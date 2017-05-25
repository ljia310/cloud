package com.cloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * Created by liujia on 2017/5/24.
 */
@Configuration
@EnableAuthorizationServer // 必须
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService; // 引入security中提供的 UserDetailsService
    @Autowired
    AuthenticationManager authenticationManager; // 引入security中提供的 AuthenticationManager
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("app_id") // 配置默认的client
                .secret("app_secret")
                .authorizedGrantTypes(
                        "password", "refresh_token")
                .scopes("app").autoApprove("app")
                .and()
                .withClient("service_id") // 配置默认的client
                .secret("service_secret")
                .authorizedGrantTypes(
                        "client_credentials", "refresh_token")
                .scopes("service").autoApprove("service");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore(); //使用内存中的 token store
    }
}
