package com.example.demo.service;

import com.example.demo.model.bo.UserData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Log4j2
public class UserLocationService {

    // 這裡的泛型 String 是 Key 的型別, UserData 是 Value 的型別
    @Autowired
    private KafkaTemplate<String, UserData> kafkaTemplate;

    private static final String TOPIC = "user-location"; // 定義你的 Topic 名稱

    public void sendUserLocation(UserData userData) {
        // KafkaTemplate 使用設定好的序列化器 (StringSerializer和JsonSerializer) 自動處理序列化
        ListenableFuture<SendResult<String, UserData>> future = kafkaTemplate.send(TOPIC, userData);
        future.addCallback(new ListenableFutureCallback<SendResult<String, UserData>>() {

            @Override
            public void onSuccess(SendResult<String, UserData> result) {
                System.out.println("Sent message=[" + userData +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println("Unable to send message=[" + userData + "] due to : " + ex.getMessage());
            }
        });
    }
}
