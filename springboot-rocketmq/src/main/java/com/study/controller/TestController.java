package com.study.controller;

import com.study.config.JmsConfig;
import com.study.result.ResultView;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class TestController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @GetMapping("/test1")
    public ResultView test(String info) throws Exception {
        //创建生产信息
        Message message = new Message(JmsConfig.topic, "Tags", "123456", "发送王帅逼！".getBytes());
        SendResult sendResult = defaultMQProducer.send(message);
        return ResultView.success();
    }
}