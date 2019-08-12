package com.study.controller;

import com.study.config.ConsumerConfig;

public class ConsumerMain {

    public static void main(String[] args) throws Exception {
        ConsumerConfig consumer = new ConsumerConfig();

        //消费消息
        consumer.defaultMQPushConsumer1();

    }
}
