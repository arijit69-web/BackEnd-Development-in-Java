package com.example.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Lazy // By default, when your application starts, all beans are initialized | Spring will create an instance of each and make them available | Instead of creating all beans up front, we can specify lazy initialization | @Lazy Annotation : A bean will only be initialized in the following cases: • It is needed for dependency injection • Or it is explicitly requested
// @Primary // Instead of specifying a coach by name using @Qualifier | I simply need a coach.. I don’t care which coach | If there are multiple coaches | Then you coaches figure it out... and tell me who’s the primary coach
public class TrackCoach implements Coach {

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k !";
    }
}
