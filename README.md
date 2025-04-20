# complex-event-processer
即時事件分析(簡單模擬資料流)  
Spring Boot + Kafka + H2  
利用 Kafka Listener 監聽 Kafka Topic，並將資料寫入 資料庫

=> 模擬 Event Producer
![img.png](img.png)
Publish User Location Event to Kafka topic

=> 模擬 Event Consumer
![img_1.png](img_1.png)
Consume User Location Event from Kafka topic and save to H2 database
![img_3.png](img_3.png)

=> 使用 docker-compose 啟動 Kafka 和 Zookeeper
```bash
docker-compose up -d
```
![img_2.png](img_2.png)

