package com.example.springdemo.mvc.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Create a Custom Annotation File and name it CourseCode
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// Helper class that contains business rules / validation logic
@Target({ElementType.METHOD, ElementType.FIELD}) // We can apply our annotation on a method or on a field
@Retention(RetentionPolicy.RUNTIME)
// So this basically says how long will the marked annotation be stored or used? | So basically we say retain this annotation in the Java class file in the form of bytecode and also use it at runtime by the JVM.
public @interface CourseCode { // @interface: Special syntax to define a custom annotation

    // define default course code
    public String value() default "LUV";

    // define default error message
    public String message() default "must start with LUV";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
    /*
    That basically says this annotation has a parameter called 'value()'. Now, if the programmer doesn't provide a value in the @CourseCode annotation, then we'll use the default value of LUV.
    And then, we'll also have a parameter called 'message()'. And if the programmer doesn't provide a message in the @CourseCode annotation, then we'll use the default message of 'must start with LUV'
    The annotation @CourseCode will remain customizable so that programmers can set the value and message of the @CourseCode annotation as per their wish and if they don't provide one the default value will be 'LUV' and the default message will be 'must start with LUV'.
    */
}
/*
Example of our annotation:
@CourseCode(value="LUV", message="must start with LUV")
private String courseCode; // Field in our class: Customer.java
*/