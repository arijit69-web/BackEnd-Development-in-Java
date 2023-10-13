package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // Guarantees order of when Aspects are applied | Lower numbers have higher precedence | Range: Integer.MIN_VALUE to Integer.MAX_VALUE | Negative numbers are allowed | Does not have to be consecutive

public class MyCloudLogAspect {
    // Need a fully qualified classname: <Package_Name>+<Class_Name> : "com.example.aopdemo.aspect.AOPDeclarations.forDaoPackageNoGetterSetter()"
    @Before("com.example.aopdemo.aspect.AOPDeclarations.forDaoPackageNoGetterSetter()") // Apply pointcut declaration to advice
    public void cloudLogAspect() // Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Checking Cloud Logs!");
    }
}
