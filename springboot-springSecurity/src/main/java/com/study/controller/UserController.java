package com.study.controller;

import com.study.result.ResultView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get1/{name}")
    public ResultView getName1(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get2/{name}")
    public ResultView getName2(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get3/{name}")
    public ResultView getName3(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get34{name}")
    public ResultView getName4(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get5/{name}")
    public ResultView getName5(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get6/{name}")
    public ResultView getName6(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get7/{name}")
    public ResultView getName7(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get8/{name}")
    public ResultView getName8(@PathVariable String name) {
        return ResultView.success(name);
    }

    @GetMapping("/get9/{name}")
    public ResultView getName9(@PathVariable String name) {
        return ResultView.success(name);
    }
}