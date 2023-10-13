package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
// Guarantees order of when Aspects are applied | Lower numbers have higher precedence | Range: Integer.MIN_VALUE to Integer.MAX_VALUE | Negative numbers are allowed | Does not have to be consecutive

public class MyLoggingDemoAspect {
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
