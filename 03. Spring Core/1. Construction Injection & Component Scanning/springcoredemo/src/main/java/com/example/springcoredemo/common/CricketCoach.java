package com.example.springcoredemo.common;
// package com.example.springcoredemo.common: common is a subpackage of our main springcoredemo application. So they'll be component scanned automatically for us using the default component scanning of Spring Boot.
import com.example.springcoredemo.common.Coach;
import org.springframework.stereotype.Component;

// Define the dependency class that implements the interface
// IDE says " no usages " But Why? Spring Framework is dynamic. IDE may not be able to determine if a given class/method is used at runtime.
@Component // It marks the class as Spring Bean & also makes the bean available for dependency injection
public class CricketCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 mins!";

    }
}
