package com.example.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringcoredemoApplication.class, args); // SpringApplication  bootstrap our Spring Boot application, and then we give a reference here to the actual name of our class, in this case, SpringcoredemoApplication

    }

}
