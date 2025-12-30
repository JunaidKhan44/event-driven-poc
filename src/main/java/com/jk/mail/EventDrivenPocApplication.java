package com.jk.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventDrivenPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenPocApplication.class, args);
	}

}
