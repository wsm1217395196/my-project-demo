package com.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	 @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer
	                .tokenKeyAccess("permitAll()") //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
	                .checkTokenAccess("isAuthenticated()") //url:/oauth/check_token allow check token
	                .allowFormAuthenticationForClients();
	    }
	    
	    /**
	     * 注入authenticationManager
	     * 来支持 password grant type
	     */
	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints.authenticationManager(authenticationManager);
	        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        clients.inMemory()
	                .withClient("client")
	                .secret("{noop}secret")
	                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
	                .scopes("scope")
	                .resourceIds("resourcesId")
	                .accessTokenValiditySeconds(1200)
	                .refreshTokenValiditySeconds(50000);
	}
	
}