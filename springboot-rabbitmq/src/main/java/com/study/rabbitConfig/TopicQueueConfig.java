package com.study.rabbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置主题队列
 */
@Configuration
public class TopicQueueConfig {

    /**
     * 配置主题队列
     *
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue2");
    }

    /**
     * 声明一个Topic类型的交换机
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 队列绑定交换机,并且指定routingKey
     * 说明：生产者P发送消息到交换机X，type=topic，交换机根据绑定队列的routing key的值进行通配符匹配；
     * 符号#：匹配一个或者多个词 topic.# 可以匹配 topic.Msg.1或者topic.Msg
     * 符号*：只能匹配一个词 topic.* 可以匹配 topic.Msg或者topic.Msg1
     */
    @Bean
    public Binding bindingTopicExchange1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.msg");
    }

    @Bean
    public Binding bindingTopicExchange2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#");
    }

}
