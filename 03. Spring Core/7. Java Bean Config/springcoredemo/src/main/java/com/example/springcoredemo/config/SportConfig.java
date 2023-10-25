package com.example.springcoredemo.config;

import com.example.springcoredemo.common.Coach;
import com.example.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {


    /*

    // SwimCoach did not have the @Component annotation and then we simply configured this as a Spring Bean using the @Bean annotation

    // The bean id defaults to the method name, so the method name in this example is swimCoach()
    @Bean // Make an existing third-party class available to Spring Framework for dependency injection | You may not have access to the source code of third-party class | However, you would like to use the third-party class as a Spring Bean
    public Coach swimCoach() { // Why we choose our method's return type as Coach instead of SwimCoach? It is a good OOP practice called always code to interface.
        return new SwimCoach(); // Return an instance of SwimCoach
    }

    */

    // Using custom Bean ID
    @Bean("Aquatic") // Make an existing third-party class available to Spring Framework for dependency injection | You may not have access to the source code of third-party class | However, you would like to use the third-party class as a Spring Bean
    public Coach swimCoach() { // Why we choose our method's return type as Coach instead of SwimCoach? It is a good OOP practice called always code to interface.
        return new SwimCoach(); // Return an instance of SwimCoach
    }

}
