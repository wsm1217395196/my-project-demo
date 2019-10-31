package com.study.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class RabbitConsumer {

    /**
     * 监听队列（第一种方式）
     *
     *
     * @param msg
     */
    @RabbitListener(queues = "simpleQueue")
    public void simpleConsumer1(String msg) {
        System.out.println("简单消费消息1 = " + msg);
    }

    @RabbitListener(queues = "simpleQueue")
    public void simpleConsumer2(String msg) {
        System.out.println("简单消费消息2 = " + msg);
    }

    @RabbitListener(queues = "fanoutQueue1")
    public void fanoutConsume1(String msg) {
        System.out.println("订阅消费消息1 = " + msg);
    }

    @RabbitListener(queues = "fanoutQueue2")
    public void fanoutConsume2(String msg) {
        System.out.println("订阅消费消息2 = " + msg);
    }

    @RabbitListener(queues = "directQueue1")
    public void directConsumer1(String msg) {
        System.out.println("路由消费消息1 = " + msg);
    }

    @RabbitListener(queues = "directQueue2")
    public void directConsumer2(String msg) {
        System.out.println("路由消费消息2 = " + msg);
    }

    @RabbitListener(queues = "topicQueue1")
    public void topicConsumer1(String msg) {
        System.out.println("主题消费消息1 = " + msg);
    }

    @RabbitListener(queues = "topicQueue2")
    public void topicConsumer2(String msg) {
        System.out.println("主题消费消息2 = " + msg);
    }
}
