package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component // It will discover that this is an aspect using Component Scanning
public class MyDemoLoggingAspect {

    /*
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    private void forDAOPackage() {
    } // Name of the pointcut declaration: "forDAOPackage()" | Can have any name | Easily reuse pointcut expressions | Update pointcut in one location

    // create pointcut for getter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    private void getter() {
    }

    // create pointcut for setter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    private void setter() {
    }

    // Combining Pointcut: Include forDaoPackage() but exclude getter() || setter()
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {
    }
    */


    // @Before("execution(public void addAccount())") // Pointcut Expression | There will be a match on addAccount() for both AccountDAO and MembershipDAO
    // @Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount())") // There will be a match on addAccount() for AccountDAO only
    // @Before("execution(public void add*())") // Match methods starting with "add" in any class
    // @Before("execution(void add*())") // Match methods with void return type
    // @Before("execution(* add*())") // // Match methods with any return type
    // @Before("execution(* add*(com.example.aopdemo.Account))") // Match methods starting with "add" that have Account as a parameter
    // @Before("execution(* add*(..))") // Match methods starting with "add" that have any number of arguments as a parameter
    // @Before("execution(* add*(com.example.aopdemo.Account, ..))")// Match methods starting with "add" that have "Account" as a first parameter, followed by any number of parameters
    // @Before("execution(* com.example.aopdemo.dao.*.*(..))") // Match any method in our DAO package: com.examplee.aopdemo.dao

    /*
    @Before("forDaoPackageNoGetterSetter()") // Apply pointcut declaration to advice
    public void beforeAddAccountAdvice() // Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Before Add Account!");
    }

    @Before("forDaoPackageNoGetterSetter()") // Apply pointcut declaration to advice
    public void performApiAnalytics() // Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Checking API Analytics!");
    }

    @Before("forDaoPackageNoGetterSetter()") // Apply pointcut declaration to advice
    public void cloudLogAspect() // Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Checking Cloud Logs!");
    }
    */

}
