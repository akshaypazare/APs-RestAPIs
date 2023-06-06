package com.RestAPIs;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@SpringBootApplication
public class RestApIsByAkshayPazareApplication {

	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public Random getRandom(){
		return new Random();
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApIsByAkshayPazareApplication.class, args);
	}

}
