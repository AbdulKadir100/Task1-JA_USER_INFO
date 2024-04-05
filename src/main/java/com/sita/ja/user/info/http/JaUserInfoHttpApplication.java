package com.sita.ja.user.info.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JaUserInfoHttpApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaUserInfoHttpApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	} 
}
