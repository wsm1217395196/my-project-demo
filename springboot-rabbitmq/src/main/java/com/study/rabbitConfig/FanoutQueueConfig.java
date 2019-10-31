package com.study.rabbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 配置订阅队列
 */
@Configuration
public class FanoutQueueConfig {

    /**
     * 配置订阅队列
     *
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue2");
    }


    /**
     * 声明一个Fanout类型的交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding bindingFanoutExchange1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutExchange2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
