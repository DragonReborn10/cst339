package com.gcu.activity2part3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.gcu" })
public class Activity2part3Application {

    public static void main(String[] args) {
        SpringApplication.run(Activity2part3Application.class, args);
    }
}
