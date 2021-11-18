package com.study.controller;

import com.study.config.AddLog;
import com.study.result.PageParam;
import com.study.result.ResultView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "测试")
@RequestMapping("/test")
public class TestController {

    //指定日志
    Logger logger = LoggerFactory.getLogger("testAppoint");

    @ApiOperation("打info/error日志")
    @GetMapping("/get")
    public ResultView<String> get() {
        log.info("info==========> 姓名：{}，年龄：{}", "wsm", 25);
        log.error("error==========> 姓名：{}，年龄：{}", "wsm", 25);
        return ResultView.success("wsm");
    }

    @AddLog(desc = "测试AOP给指定文件写日志", interfaceParam = "#name")
    @ApiOperation("测试AOP给指定文件写日志")
    @PostMapping("/get1")
    public ResultView<String> get1(@RequestParam String name, @RequestParam Integer age, @RequestBody PageParam pageParam) {
        logger.info("info==========> 测试给指定文件写日志：{}", 666);
        logger.error("error==========> 测试给指定文件写日志：{}", 666);
        return ResultView.success(name);
    }
}
