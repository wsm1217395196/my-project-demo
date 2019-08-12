package com.study.controller;

import com.study.config.ConsumerConfig;
import com.study.config.ProducerConfig;

public class ProduceMain {

    public static void main(String[] args) throws Exception {
        ProducerConfig producer = new ProducerConfig();

        //同步生产消息
        producer.defaultMQProducer1();

    }
}
