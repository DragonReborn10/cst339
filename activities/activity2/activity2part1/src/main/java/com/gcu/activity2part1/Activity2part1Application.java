package com.gcu.activity2part1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.gcu" })

@SpringBootApplication
public class Activity2part1Application {

	public static void main(String[] args) {
		SpringApplication.run(Activity2part1Application.class, args);
	}

}
