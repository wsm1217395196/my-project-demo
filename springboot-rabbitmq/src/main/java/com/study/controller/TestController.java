package com.study.controller;

import com.study.result.ResultView;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.基本(简单)消息模型：生产者–>队列–>一个消费者
 * 2.work消息模型：生产者–>队列–>多个消费者共同消费(只能其中一个消费，负载均衡)
 * 3.订阅模型-Fanout：广播，将消息交给所有绑定到交换机的队列，每个消费者都可以收到同一条消息
 * 4.订阅模型-Direct：定向，把消息交给符合指定 rotingKey 的队列（路由模式）
 * 5.订阅模型-Topic：通配符，把消息交给符合routing pattern（主题模式） 的队列
 * （3、4、5这三种都属于订阅模型，只不过进行路由的方式不同。)
 */
@RestController
@RequestMapping
public class TestController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 一对一模式，工作模式（多个消费者，只能其中一个消费，负载均衡）
     * 生产者将消息发送到队列，消费者从队列中获取消息。
     *
     * @param msg
     * @return
     */
    @GetMapping("/test1")
    public ResultView test1(@RequestParam String msg) {
        for (int i = 0; i < 2; i++) {
            amqpTemplate.convertAndSend("simpleQueue", msg);
        }
        return ResultView.success();
    }

    /**
     * 订阅模式
     * 解读：
     * 1、1个生产者，多个消费者
     * 2、每一个消费者都有自己的一个队列
     * 3、生产者没有将消息直接发送到队列，而是发送到了交换机
     * 4、每个队列都要绑定到交换机
     * 5、生产者发送的消息，经过交换机，到达队列，实现，一个消息被多个消费者获取的目的
     * 注意：一个消费者队列可以有多个消费者实例，只有其中一个消费者实例会消费
     *
     * @param msg
     * @return
     */
    @GetMapping("/test2")
    public ResultView test2(@RequestParam String msg) {
        amqpTemplate.convertAndSend("fanoutExchange", "", msg);
        return ResultView.success();
    }

    /**
     * 路由模式
     * 生产者发送消息到交换机，type=direct，交换机根据绑定队列的routing key的值进行匹配，匹配到消费者消费消息
     *
     * @return
     */
    @GetMapping("/test3")
    public ResultView test3(@RequestParam String msg) {
        amqpTemplate.convertAndSend("directExchange", "direct.msg1", msg);
        return ResultView.success();
    }

    /**
     * 主题模式---比路由模式多了通配符
     * 说明：生产者发送消息到交换机，type=topic，交换机根据绑定队列的routing key的值进行通配符匹配；匹配到消费者消费消息
     * 符号#：匹配一个或者多个词 topic.# 可以匹配 topic.Msg.1或者topic.Msg
     * 符号*：只能匹配一个词 topic.* 可以匹配 topic.Msg或者topic.Msg1
     */
    @GetMapping("/test4")
    public ResultView test4(@RequestParam String msg) {
        amqpTemplate.convertAndSend("topicExchange", "topic.msg", msg);
        return ResultView.success();
    }
}
