package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
// If you only have pointcuts then @Aspect is optional | Only required if you add advices in this class: @Before, @After etc
public class AOPDeclarations {
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDAOPackage() {
    } // Name of the pointcut declaration: "forDAOPackage()" | Can have any name | Easily reuse pointcut expressions | Update pointcut in one location

    // create pointcut for getter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    // create pointcut for setter methods
    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    // Combining Pointcut: Include forDaoPackage() but exclude getter() || setter()
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }

}
