package com.study.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    /**
     * 点对点消息，消费者监听主题就可以了
     *
     * @param consumerRecord
     */
    @KafkaListener(topics = "topic1")
    public void consumer1(ConsumerRecord consumerRecord) {
        Object value = consumerRecord.value();
        System.err.println("点对点消息1 = " + value);
    }

    /**
     * 订阅消息，消费者监听主题，定义不同groupId就可以了
     *
     * @param consumerRecord
     */
    @KafkaListener(topics = "topic2", groupId = "group1")
    public void consumer2(ConsumerRecord consumerRecord) {
        Object value = consumerRecord.value();
        System.err.println("订阅消息1 = " + value);
    }

    @KafkaListener(topics = "topic2", groupId = "group2")
    public void consumer3(ConsumerRecord consumerRecord) {
        Object value = consumerRecord.value();
        System.err.println("订阅消息2 = " + value);
    }
}
