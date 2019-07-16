package com.study.config;

import com.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().
                antMatchers("/user/get1/**").permitAll().//可以访问
                antMatchers("/user/get2/**").denyAll().//永远不能访问
                antMatchers("/user/get3/**").anonymous().//不登录（匿名用户）可以访问
                antMatchers("/user/get4/**").rememberMe().//当前用户若是 rememberMe 可以访问（？？？还不知道是啥rememberMe）
                antMatchers("/user/get5/**").fullyAuthenticated().//当前用户若既不是匿名用户又不是 rememberMe 可以访问（登录认证了也可以访问）
                antMatchers("/user/get6/**").hasAnyRole("ADMIN", "USER").//拥有其中一个角色（ROLE_USER，ROLE_ADMIN）可以访问
                antMatchers("/user/get7/**").hasRole("USER").//拥有ROLE_USER角色才可以访问
                anyRequest()//其他的意思
                .authenticated();//当前用户（已认证）才可以访问
        http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
