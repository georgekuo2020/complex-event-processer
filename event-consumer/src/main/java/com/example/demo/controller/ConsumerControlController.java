package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "操作 消費者")
@RestController
@RequestMapping("/consumer")
@Log4j2
public class ConsumerControlController {

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    private static final String LISTENER_ID = "userLocationListener";

    @GetMapping("/start")
    public String startConsumer() {
        MessageListenerContainer container = registry.getListenerContainer(LISTENER_ID);

        if (container == null) {
            return "Error: Listener container with ID '" + LISTENER_ID + "' not found.";
        }

        if (container.isRunning()) {
            return "Consumer with ID '" + LISTENER_ID + "' is already running.";
        } else {
            // 啟動 Listener Container
            container.start();
            return "Consumer with ID '" + LISTENER_ID + "' started.";
        }
    }

    @GetMapping("/stop")
    public String stopConsumer() {
        MessageListenerContainer container = registry.getListenerContainer(LISTENER_ID);

        if (container == null) {
            return "Error: Listener container with ID '" + LISTENER_ID + "' not found.";
        }

        if (container.isRunning()) {
            // 停止 Listener Container
            container.stop();
            return "Consumer with ID '" + LISTENER_ID + "' stopped.";
        } else {
            return "Consumer with ID '" + LISTENER_ID + "' is already stopped.";
        }
    }

    @GetMapping("/status")
    public String getConsumerStatus() {
        MessageListenerContainer container = registry.getListenerContainer(LISTENER_ID);

        if (container == null) {
            return "Error: Listener container with ID '" + LISTENER_ID + "' not found.";
        }
        return "Consumer with ID '" + LISTENER_ID + "' is " + (container.isRunning() ? "running." : "stopped.");
    }
}
