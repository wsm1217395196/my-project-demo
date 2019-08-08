package com.study.controller;

import com.study.config.ConsumerConfig;
import com.study.config.ProducerConfig;

public class TestMain {

    public static void main(String[] args) throws Exception {
        ProducerConfig producer = new ProducerConfig();
        producer.defaultMQProducer1();

        ConsumerConfig consumer = new ConsumerConfig();
    }
}
