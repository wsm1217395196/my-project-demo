package com.study.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map map = new HashMap();
//        shiroFilterFactoryBean.setLoginUrl("/login");//登录
//        map.put("/logout", "logout");//登出
        map.put("/test1", "anon");//表示可匿名访问
        map.put("/test2", "authc");//表示需要认证才能访问
        map.put("/test3", "user");//表示用户不一定需要通过认证，只要曾被 Shiro 记住过登录状态就可以正常发起请求
        map.put("/test4", "perms[admin1:test4]");//表示用户必需已通过认证，并拥有 admin:test4 权限才可以正常发起 /test4 请求
        map.put("/test5", "roles[admin1,admin2]");//表示用户必需已通过认证，并拥有 admin 或者admin1角色才可以正常发起 /admin 请求
//        shiroFilterFactoryBean.setSuccessUrl("/index");//首页
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");//错误页面，认证不通过跳转
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    /**
     * 将自己的验证方式加入容器
     */
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

}
