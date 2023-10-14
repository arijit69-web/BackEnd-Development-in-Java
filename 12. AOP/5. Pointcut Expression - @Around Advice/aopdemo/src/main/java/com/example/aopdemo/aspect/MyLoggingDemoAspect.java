package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
// Guarantees order of when Aspects are applied | Lower numbers have higher precedence | Range: Integer.MIN_VALUE to Integer.MAX_VALUE | Negative numbers are allowed | Does not have to be consecutive

public class MyLoggingDemoAspect {

    @Around("execution(* com.example.aopdemo.service.*.getFortune(boolean))")

    /*
    @Around Aspect that will handle Exception
    */
    public Object aroundGetFortuneHandleException(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        Object result = null;
        // Get begin timestamp
        System.out.println("\n=====> Inside @Around Aspect");

        System.out.println("Timer Started");
        long begin = System.currentTimeMillis();

        System.out.println("Executing the method");

        // Handle / Swallow / Stop the Exception

        try { // The exception was never thrown to the main app [AopdemoApplication]. The main app [AopdemoApplication] will never know about the exception. Exception being handled in @Around Advice.
            // Now, let's execute the method
            result = theProceedingJoinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Exception occurred @Around Aspect: " + e.getMessage());
            result = "MAJOR SERVER CRASH!";
            /*
            // Simply rethrow the exception that occurred in the TrafficFortuneServiceImpl class
            throw e;
            */

        }

        System.out.println("Result: " + result);
        System.out.println("Timer Ended");
        // Get end timestamp
        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;

        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
        return result;

    }


    /*
    @Around advice allows you to intercept a method invocation, meaning that you can execute custom code before and after the method call.
    theProceedingJoinPoint.proceed() is used to invoke/execute the intercepted method [getFortune() in the Service Class for this case], and the result of that method's execution is stored in the result [Result: 'Your Fortune is very Good!'jost from the Service Class in this case] variable. This allows you to perform additional actions before and after the method's execution, as well as potentially modify or monitor the method's behavior.
    */
    @Around("execution(* com.example.aopdemo.service.*.getFortune())")

    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // Get begin timestamp
        System.out.println("\n=====> Inside @Around Aspect");

        System.out.println("Timer Started");
        long begin = System.currentTimeMillis();

        System.out.println("Executing the method");
        // Now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        System.out.println("Result: " + result);
        System.out.println("Timer Ended");
        // Get end timestamp
        long end = System.currentTimeMillis();

        // Compute duration and display it
        long duration = end - begin;

        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");
        return result;

    }

    // Regardless of success or failure, the @After advice will always run
    @After( // @AfterThrowing: This is an annotation in Spring AOP that marks a method as advice that should be executed after a specified pointcut when an exception is thrown. In this case, it specifies that the advice method should run after an exception is thrown.
            "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinally(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Finally Block");
    }

    /*
    @AfterThrowing Advice - Use Cases:
        Log the exception
        Perform auditing on the exception
        Notify DevOps team via email or SMS
        Encapsulate this functionality in AOP aspect for easy reuse

    At this point, we are only intercepting the exception (Reading it)
    However, the exception is still propagated to calling program
    */
    @AfterThrowing( // @AfterThrowing: This is an annotation in Spring AOP that marks a method as advice that should be executed after a specified pointcut when an exception is thrown. In this case, it specifies that the advice method should run after an exception is thrown.
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theException")
// throwing = "theException": This part of the @AfterThrowing annotation specifies that the advice method should receive the thrown exception as an argument and bind it to the variable theException. This allows you to access information about the exception that was thrown.
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theException) {// Throwable is the root class of the Java exception hierarchy. All exceptions and errors in Java extend from Throwable. It has two main subclasses: Exception and Error.
        System.out.println("\n=====>>> Exception is: " + theException);
    }

    // @AfterReturning: The @AfterReturning annotation is used in Aspect-Oriented Programming (AOP) with frameworks like Spring to define advice that should be executed after a specified method successfully returns / after a specified method successfully executed.
    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
// result Parameter: The 'result' parameter is used to capture the return value of the findAccounts method. In the context of this advice, it's a way to access the result returned by findAccounts and use it within the advice. You can think of it as a way to retrieve and work with the data that findAccounts returns.
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        System.out.println("\n=====>>> Result is: " + result);
        if (!result.isEmpty())
            result.set(0, new Account("Specialist Programmer II", "Mayank")); // Modify the Return Value | Post-processing Data: Post process the data before returning to caller | Format the data or enrich the data before returning to caller
    }


    // Need a fully qualified classname: <Package_Name>+<Class_Name> : com.example.aopdemo.aspect.AOPDeclarations.forDaoPackageNoGetterSetter()
    @Before("com.example.aopdemo.aspect.AOPDeclarations.forDaoPackageNoGetterSetter()")
    // Apply pointcut declaration to advice
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) // JoinPoint has metadata about method call | Run this method/code BEFORE - target object method: "public void add Account()"
    {
        System.out.println("Before Add Account!");

        // Display the method signature

        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println(methodSignature);


        // Display the  method arguments

        Object[] args = theJoinPoint.getArgs();

        for (Object obj : args) {
            System.out.println("Object: " + obj);

            if (obj instanceof Account) {
                // Downcast and print Account specific data
                Account theAccount = (Account) obj;
                System.out.println(theAccount.getName() + " | " + theAccount.getLevel());
            }
        }


    }
}
