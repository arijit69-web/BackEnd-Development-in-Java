package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
@SpringBootApplication: This annotation is a combination of several annotations, including @Configuration, @ComponentScan, and @EnableAutoConfiguration. It is used to configure and bootstrap a Spring application.
@Configuration: This annotation is automatically added by @SpringBootApplication. It indicates that the class contains Spring bean definitions and should be processed by the Spring container to configure the application context. In other words, the @SpringBootApplication annotation already includes @Configuration under the hood, so you don't need to add it explicitly.
*/
@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);

    }

    /*
    CommandLineRunner provides run() method which is called after application context is loaded but before the execution of the main method is complete.

    E.g. You have need to run a Scheduled Batch Job, set some system environment properties, or need to perform some DB operation just before the Spring Boot run() method is finished, so in this kind of scenario CommandLineRunner Interface comes handy. It allows you to do such operation before the Spring Boot's run() method finishes its execution.

    CommandLineRunner interface is used for running code at application startup, just after the Spring ApplicationContext has been created. While you can certainly use the main method for this purpose as well, there are several advantages to using CommandLineRunner.

        1. Spring Context: When you implement CommandLineRunner, the run method is executed after the application context is fully started. This means you can access all the Spring features, such as dependency injection, transaction management, etc., directly in the run method.

        2. Exception Handling: Since the CommandLineRunner integrates well with the Spring ecosystem, you can leverage Spring’s exception-handling mechanisms seamlessly.

        3. Decoupling: Using CommandLineRunner helps to keep your startup logic separate from the main application logic. This makes the code easier to understand and maintain.

    In summary, while you can certainly use the main method for running code at startup, using CommandLineRunner offers several benefits in terms of clean code organization, Spring context availability, and modularity.
    */
    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {// Spring Boot will inject the dependency AccountDAO automatically because of @Bean | When using @Bean for a method such as commandLineRunner, Spring will check the arguments to the method. Spring will resolve the method arguments by injecting the appropriate Spring Bean. In this case, there is no need to use @Autowired. Spring will inject the beans automatically behind the scenes.
        return runner -> {
            demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        theAccountDAO.addAccount(new Account("Arijit", "Specialist Programmer"), true);
        System.out.println(theMembershipDAO.addData());
        theMembershipDAO.addAccount();
    }
}
