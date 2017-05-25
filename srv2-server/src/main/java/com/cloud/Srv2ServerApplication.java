package com.cloud;


import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableResourceServer
public class Srv2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Srv2ServerApplication.class, args);
    }

    // Resource Server Token Relay
    // https://stackoverflow.com/questions/29439653/spring-cloud-feign-with-oauth2resttemplate

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
                        SecurityContextHolder.getContext().getAuthentication().getDetails();

                requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
            }
        };
    }

}
