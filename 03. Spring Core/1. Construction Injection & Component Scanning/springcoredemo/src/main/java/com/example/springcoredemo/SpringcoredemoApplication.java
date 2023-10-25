package com.example.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.springcoredemo", "com.example.util"}) // Explicitly list base packages for Component Scanning | By default it only component scan springcoredemo, but we have this util, so we have to list both of those here.

// It enables  auto configuration, component scanning, and additional configuration | Behind the scene this annotation is composed of the following annotations, @EnableAutoConfiguration, @ComponentScan and @Configuration.

public class SpringcoredemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringcoredemoApplication.class, args); // SpringApplication  bootstrap our Spring Boot application, and then we give a reference here to the actual name of our class, in this case, SpringcoredemoApplication

    }

}
