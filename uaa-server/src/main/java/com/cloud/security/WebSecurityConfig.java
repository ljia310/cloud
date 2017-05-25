package com.cloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by liujia on 2017/5/24.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // oauth server 不需要 csrf 防护
                .authorizeRequests()
                .antMatchers("/public/**").permitAll() // public 路径不需要认证即可访问
                .anyRequest().authenticated() //其他页面都需要登录后访问
                .and()
                .httpBasic().disable(); // 禁止 basic 认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/info");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("1")
                .roles("USERS");
    }

    // 将 AuthenticationManager 注册为 bean , 方便配置 oauth server 的时候使用
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // 将 UserDetailService 注册为 bean , 方便配置 oauth server 的时候使用
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
