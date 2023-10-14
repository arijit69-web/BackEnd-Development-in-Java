package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {

        try {
            System.out.println("Sleeping Start");
            // Simulate a Delay of 5 sec
            TimeUnit.SECONDS.sleep(5);
            System.out.println("Sleeping End");

        } catch (InterruptedException Exc) {
            throw new RuntimeException(Exc);
        }
        // Return a Fortune
        return "Your Fortune is very Good!";
    }

    @Override
    public String getFortune(boolean tripWire) {
        if (tripWire) {

            throw new RuntimeException("Major Exception! HIGH ALERT");

        }
        return getFortune();

    }
}
