package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
	    http.requestMatchers().antMatchers("/oauth/**")
	    .and()
	    .authorizeRequests()
	    .antMatchers("/oauth/**").permitAll();
	 }


	/**
	 * 需要配置这个支持password模式 support password grant type
	 * @return
	 * @throws Exception
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}