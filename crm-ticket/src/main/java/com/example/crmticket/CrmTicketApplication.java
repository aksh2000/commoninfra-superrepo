package com.example.crmticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CrmTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmTicketApplication.class, args);
	}
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
