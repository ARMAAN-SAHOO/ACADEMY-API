package com.armaan.academyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcademyapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyapiApplication.class, args);
	}

}
