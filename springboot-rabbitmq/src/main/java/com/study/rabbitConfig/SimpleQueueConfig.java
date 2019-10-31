package com.study.rabbitConfig;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置一对一，工作模式队列
 */
@Configuration
public class SimpleQueueConfig {

    /**
     * 一对一，工作模式
     * 生产者将消息发送到队列，消费者从队列中获取消息。
     *
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simpleQueue");
    }
}
