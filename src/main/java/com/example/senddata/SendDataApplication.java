package com.example.senddata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={"com.example.senddata"})
@ComponentScan("com.example.senddata")
public class SendDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(SendDataApplication.class, args);
	}
}
