package com.example.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Creates a new bean instance for each container request
public class BaseballCoach implements Coach {

    public BaseballCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());

    }

    // Define our init method
    @PostConstruct // The PostConstruct annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization. | Constructor V/S PostConstruct: When the constructor is called, the bean is not yet initialized - i.e. no dependencies are injected. In the @PostConstruct method the bean is fully initialized and you can use the dependencies.
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());

    }


    // Define our destroy method
    @PreDestroy // The PreDestroy annotation is used on methods as a callback notification to signal that the instance is in the process of being removed by the container. The method annotated with PreDestroy is typically used to release resources that it has been holding.
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());

    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 mins in batting practice !";
    }
}

/*
Special Note about PROTOTYPE Scope - Destroy Lifecycle Method and Lazy Init

    Prototype Beans and Destroy Lifecycle
        There is a subtle point you need to be aware of with "PROTOTYPE" scoped beans.
        For "PROTOTYPE" scoped beans, Spring does not call the destroy method.

        In contrast to the other scopes, Spring does not manage the complete lifecycle of a PROTOTYPE bean: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.
        Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, in the case of prototypes, configured destruction lifecycle callbacks are not called. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.

    PROTOTYPE Beans and Lazy Initialization
        PROTOTYPE beans are lazy by default. There is no need to use the @Lazy annotation for PROTOTYPE scopes beans.
*/