package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableResourceServer//必须要
public class UaaServerApplication {

	@RequestMapping("/user")
	public Principal user(Principal user){
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(UaaServerApplication.class, args);
	}
}
