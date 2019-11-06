package com.study.controller;

import com.study.result.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 点对点消息，消费者监听主题就可以了
     *
     * @return
     */
    @GetMapping("/test1")
    public ResultView test1() {
        kafkaTemplate.send("topic1", "点对点卡发卡消息！");
        return ResultView.success();
    }

    /**
     * 订阅消息，消费者监听主题，定义不同groupId就可以了
     *
     * @return
     */
    @GetMapping("/test2")
    public ResultView test2() {
        kafkaTemplate.send("topic2", "订阅卡发卡消息！");
        return ResultView.success();
    }
}
