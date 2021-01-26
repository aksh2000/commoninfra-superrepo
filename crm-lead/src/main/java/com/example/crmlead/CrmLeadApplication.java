package com.example.crmlead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CrmLeadApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmLeadApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

}
