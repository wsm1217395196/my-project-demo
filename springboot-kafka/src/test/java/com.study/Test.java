package com.study;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 点对点消息，消费者监听主题就可以了
     *
     * @return
     */
    @org.junit.Test
    public void test1() {
        kafkaTemplate.send("topic1", "点对点卡发卡消息！");
    }

    /**
     * 订阅消息，消费者监听主题，定义不同groupId就可以了
     *
     * @return
     */
    @org.junit.Test
    public void test2() {
        kafkaTemplate.send("topic2", "订阅卡发卡消息！");
    }
}
