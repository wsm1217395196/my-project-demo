package com.study.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单模式消费者
 * 监听队列（第二种方式）
 */
@Component
@RabbitListener(queues = "simpleQueue")
public class SimpleConsumer2 {

    @RabbitHandler
    public void simpleConsumer(String msg) {
        System.err.println("简单消费消息2 = " + msg);
    }
}
