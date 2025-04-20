package com.example.demo.schedule;

import com.example.demo.model.bo.UserData;
import com.example.demo.service.UserLocationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
@Log4j2
public class ScheduleUserLocationProducer {

    @Autowired
    private UserLocationService userLocationService;

    private final String[] locations = {"大安區", "士林區", "北投區", "中山區", "中正區", "板橋區", "士林區", "新店區", "土城區", "中和區", "永和區"};

    private final Random random = new Random();

    // 排程任務
    @Scheduled(fixedRate = 2000) // 毫秒
    public void produceRandomUserLocation() {

        // 生成模擬 uid
        String uid = UUID.randomUUID().toString().replace("-", "");
        // 隨機選擇一個 location
        String location = locations[random.nextInt(locations.length)];

        // 創建 UserData 物件
        UserData userData = new UserData(uid, location);

        log.info("send user data {}", userData.toString());

        // 發送 UserData 到 Kafka
        userLocationService.sendUserLocation(userData);
    }
}
