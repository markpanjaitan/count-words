package com.markvp.countwords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.markvp.countwords")
public class CountWordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountWordsApplication.class, args);
	}

}
