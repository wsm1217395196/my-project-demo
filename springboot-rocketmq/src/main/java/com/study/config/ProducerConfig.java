package com.study.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生产者
 */
@Configuration
public class ProducerConfig {

    public void defaultMQProducer1() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer(JmsConfig.producerName);
        producer.setNamesrvAddr(JmsConfig.namesrvAddr);
        producer.setVipChannelEnabled(false);
        producer.setSendMsgTimeout(10000);
        producer.start();
        for (int i = 0; i < 3; i++) {
            Message msg = new Message(JmsConfig.topic, "tag", "key", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, 10);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }


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
}
