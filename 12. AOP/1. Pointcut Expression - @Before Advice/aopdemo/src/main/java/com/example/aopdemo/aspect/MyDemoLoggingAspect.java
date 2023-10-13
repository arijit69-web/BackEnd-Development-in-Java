package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component // It will discover that this is an aspect using Component Scanning
public class MyDemoLoggingAspect {

    // @Before("execution(public void addAccount())") // Pointcut Expression | There will be a match on addAccount() for both AccountDAO and MembershipDAO
    // @Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount())") // There will be a match on addAccount() for AccountDAO only
    // @Before("execution(public void add*())") // Match methods starting with "add" in any class
    // @Before("execution(void add*())") // Match methods with void return type
    // @Before("execution(* add*())") // // Match methods with any return type
    // @Before("execution(* add*(com.example.aopdemo.Account))") // Match methods starting with "add" that have Account as a parameter
    // @Before("execution(* add*(..))") // Match methods starting with "add" that have any number of arguments as a parameter
    // @Before("execution(* add*(com.example.aopdemo.Account, ..))")// Match methods starting with "add" that have "Account" as a first parameter, followed by any number of parameters

    @Before("execution(* com.example.aopdemo.dao.*.*(..))") // Match any method in our DAO package: com.examplee.aopdemo.dao

    public void beforeAddAccountAdvice() // Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Before Add Account");
    }
}
