package com.cms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class CmsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsUserApplication.class, args);
	}
	@Bean
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
