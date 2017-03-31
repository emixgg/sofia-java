package com.somospnt.sofiabot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class SofiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiaApplication.class, args);
    }

}
