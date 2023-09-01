package com.example.myapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    @Value("${my.name}")
    private String name;

    @Value("${city.name}")

    private String city;


    // Expose "/" that return "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public String testAPI() {
        return "Hello Arijit!";
    }

    @GetMapping("/myinfo")
    public String myInfo() {
        return "My name is " + name + " and I live in " + city + ".";
    }


}
