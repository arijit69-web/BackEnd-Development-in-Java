package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // Logger is a class that's part of the Java Logging API. It is used for logging messages and is typically employed to help developers debug and monitor their code.
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    // Match on any CLASS in the package | Match on any METHOD in the class | Match on any number of arguments
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    // Match on any CLASS in the package | Match on any METHOD in the class | Match on any number of arguments
    private void forServicePackage() {

    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    // Match on any CLASS in the package | Match on any METHOD in the class | Match on any number of arguments
    private void forDAOPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    public void forAppFLow() {
    }


    @Before(("forAppFLow()"))
    public void before(JoinPoint theJoinPoint) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> In @Before: Calling method: " + theMethod);

        Object[] args = theJoinPoint.getArgs();
        for (Object arg : args) {
            myLogger.info("=====> In @Before: Arguments of " + theMethod + ": " + arg);
        }

    }

    @AfterReturning(pointcut = "forAppFLow()", returning = "theResult")
    public void after(JoinPoint theJoinPoint, Object theResult) {
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> In @AfterReturning: Calling method: " + theMethod);

        myLogger.info("=====> In @AfterReturning: Result of " + theMethod + ": " + theResult);


    }


}
