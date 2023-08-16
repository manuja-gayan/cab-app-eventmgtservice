package com.ceyloncab.eventmgtservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class EventmgtserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventmgtserviceApplication.class, args);
	}

}
