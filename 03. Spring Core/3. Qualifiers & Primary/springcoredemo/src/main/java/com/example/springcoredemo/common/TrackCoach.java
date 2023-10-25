package com.example.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // Instead of specifying a coach by name using @Qualifier | I simply need a coach.. I don’t care which coach | If there are multiple coaches | Then you coaches figure it out... and tell me who’s the primary coach
public class TrackCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k !";
    }
}
