package com.journalLatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JornalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JornalApplication.class, args);
	}

}
