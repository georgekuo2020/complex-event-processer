package com.example.demo.consumer;

import com.example.demo.model.bo.UserDataBO;
import com.example.demo.repository.UserLocationRepository;
import com.example.demo.service.UserLocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserLocationConsumer {

    @Autowired
    UserLocationService userLocationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
            autoStartup = "false",
            topics = "user-location",
            groupId = "user_location_group",
            id = "userLocationListener")
    public void listen(String messageValue) {
        // ===> 這裡是接收到訊息後的處理邏輯 <===

        log.info("收到原始訊息值 (JSON 字串): " + messageValue); // 可以先印出原始字串確認

        UserDataBO userDataBO = null;
        try {

            // ===> 手動將 JSON 字串反序列化成 UserData 物件 <===
            userDataBO = objectMapper.readValue(messageValue, UserDataBO.class);

            // ===> 接下來就是你原來的處理邏輯，使用反序列化後的 userData 物件 <===
            log.info("consume data === {}", userDataBO);

            userLocationService.saveRecord(userDataBO);

        } catch (JsonProcessingException e) {
            // ===> 處理 JSON 反序列化失敗的情況 <===
            System.err.println("Error deserializing JSON message: " + messageValue + " - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing message: " + messageValue + " - " + e.getMessage());
        }
    }

}
