package com.study.controller;

import com.study.result.ResultView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping
public class TestController {

    @GetMapping("/testLogin")
    public ResultView login(@RequestParam String name, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        try {
            //传递token给shiro的realm
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultView.error("账号或密码错误！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return ResultView.error("您没有访问权限！");
        }
        return ResultView.success();
    }

    @GetMapping("/test1")
    public ResultView test1(HttpServletRequest request) {
        return ResultView.success();
    }

    @GetMapping("/test2")
    public ResultView test2(HttpServletRequest request) {
        return ResultView.success();
    }

    @GetMapping("/test3")
    public ResultView test3(HttpServletRequest request) {
        return ResultView.success();
    }

    @GetMapping("/test4")
    public ResultView test4(HttpServletRequest request) {
        return ResultView.success();
    }

    @GetMapping("/test5")
    public ResultView test5(HttpServletRequest request) {
        return ResultView.success();
    }
}
