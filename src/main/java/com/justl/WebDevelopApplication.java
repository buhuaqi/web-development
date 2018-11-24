package com.justl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.justl")
@SpringBootApplication
public class WebDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDevelopApplication.class, args);
	}
}
