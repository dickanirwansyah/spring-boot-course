package com.dicka.springcoronatracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringCoronaTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoronaTrackingApplication.class, args);
	}

}
