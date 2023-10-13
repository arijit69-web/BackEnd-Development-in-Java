package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
// Guarantees order of when Aspects are applied | Lower numbers have higher precedence | Range: Integer.MIN_VALUE to Integer.MAX_VALUE | Negative numbers are allowed | Does not have to be consecutive

public class MyLoggingDemoAspect {

    // @AfterReturning: The @AfterReturning annotation is used in Aspect-Oriented Programming (AOP) with frameworks like Spring to define advice that should be executed after a specified method successfully returns / after a specified method successfully executed.
    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")// result Parameter: The 'result' parameter is used to capture the return value of the findAccounts method. In the context of this advice, it's a way to access the result returned by findAccounts and use it within the advice. You can think of it as a way to retrieve and work with the data that findAccounts returns.
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
            System.out.println(obj);

            if (obj instanceof Account) {
                // Downcast and print Account specific data
                Account theAccount = (Account) obj;
                System.out.println(theAccount.getName() + " | " + theAccount.getLevel());
            }
        }


    }
}
