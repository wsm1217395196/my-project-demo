package com.study.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者
 */
@Configuration
public class ProducerConfig {

    /**
     * 创建普通消息发送者实例
     *
     * @return
     */
    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        //示例生产者
        DefaultMQProducer producer = new DefaultMQProducer(JmsConfig.producerName);
        //绑定namesrvAddr
        producer.setNamesrvAddr(JmsConfig.namesrvAddr);
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        producer.start();
        return producer;
    }


    /**
     * 同步生产消息
     *
     * @throws Exception
     */
    public void defaultMQProducer1() throws Exception {
        //示例生产者
        DefaultMQProducer producer = new DefaultMQProducer(JmsConfig.producerName);
        //绑定namesrvAddr
        producer.setNamesrvAddr(JmsConfig.namesrvAddr);
        //不开启vip通道 开通端口会减2
//        producer.setVipChannelEnabled(false);
        producer.start();
        for (int i = 0; i < 30; i++) {
            Message msg = new Message(JmsConfig.topic, "tag", "key", ("生产消息" + i).getBytes("utf-8"));
            SendResult sendResult = producer.send(msg);
            System.err.println(JmsConfig.producerName + " 生产消息" + i);
        }
        producer.shutdown();
    }
}
