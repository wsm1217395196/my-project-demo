package com.study.controller;

import com.study.result.ResultView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class Test {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/testShutdown")
    public ResultView testShutdown(Integer sleep) throws InterruptedException {
        Random random = new Random();
        String a = random.nextInt(9) + "" + random.nextInt(9);
        log.info("随机数a= " + a);
        log.info("请求线程睡眠" + (sleep / 1000) + "秒");
        Thread.sleep(sleep);
        for (int i = 0; i < 2; i++) {
            stringRedisTemplate.opsForValue().set("key-" + i, "值-" + i);
        }
        log.info("请求线程结束");
        return ResultView.success("springboot优雅停机测试成功");
    }
}
