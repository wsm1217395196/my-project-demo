package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().antMatchers("/api/**").and().authorizeRequests().antMatchers("/api/**").authenticated();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("resourcesId").stateless(true);
	}
	
}