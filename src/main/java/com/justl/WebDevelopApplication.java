package com.justl;

import com.avos.avoscloud.AVOSCloud;
import com.justl.utils.ConfigUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;

@ComponentScan(basePackages = "com.justl")
@SpringBootApplication
public class WebDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebDevelopApplication.class, args);

	}
}
