package com.gcu.activity3part3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({ "com.gcu" })
public class Activity3part3Application {

    public static void main(String[] args) {
        SpringApplication.run(Activity3part3Application.class, args);
    }
}
