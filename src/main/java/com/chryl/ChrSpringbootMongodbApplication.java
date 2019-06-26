package com.chryl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class ChrSpringbootMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChrSpringbootMongodbApplication.class, args);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	public void show(){

	}

}
