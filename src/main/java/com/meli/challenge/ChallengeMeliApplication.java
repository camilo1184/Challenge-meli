package com.meli.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.meli.challenge.entity")
public class ChallengeMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMeliApplication.class, args);
	}

}
