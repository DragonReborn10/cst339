package com.gcu.activity3part2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.gcu" })
public class Activity3part2Application {

    public static void main(String[] args) {
        SpringApplication.run(Activity3part2Application.class, args);
    }
}
