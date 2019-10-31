package com.study.rabbitConfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置路由队列
 */
@Configuration
public class DirectQueueConfig {

    /**
     * 配置路由队列
     *
     * @return
     */
    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1");
    }

    @Bean
    public Queue directQueue2() {
        return new Queue("directQueue2");
    }

    /**
     * 声明一个Direct类型的交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 队列绑定交换机,并且指定一个路由key（routingKey）
     * @return
     */
    @Bean
    public Binding bindingDirectExchange1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with("direct.msg1");
    }

    @Bean
    public Binding bindingDirectExchange2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with("direct.msg2");
    }

}
