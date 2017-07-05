package com.example.demo.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PromotionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromotionServiceApplication.class, args);
	}
}
