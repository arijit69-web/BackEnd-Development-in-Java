package com.example.springdemo.mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Helper class that contains business rules for validation
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {// CourseCode is our annotation & String is the actual type of data that we're validating against
    private String coursePrefix;

    // While creating the validator we have to initialize it. So that we can get a handle on the actual annotation that was passed in as a parameter and that is theCourseCode.
    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value(); // The .value() actually access the attribute value for that given annotation `@CourseCode(value="LUV", message="must start with LUV")`. It will actually be LUV by default and programmers could easily customize that value to be something different, e.g. `@CourseCode(value="SRKR", message="must start with SRKR")`.
        // Now the validator is set up, and it's up and running and now someone can actually use it and check if the given value entered by the user in the HTML form data is valid or not.
    }

    // Spring MVC will call isValid() @ Runtime | Adding Custom Business logic over here for the annotation @CourseCode
    @Override
    public boolean isValid(String theCode, // theCode is the HTML form data entered by the user
                           ConstraintValidatorContext theConstraintValidatorContext // To give additional error messages if we need it for this given validation routine.
    ) {
        boolean result;
        if (theCode != null) { // Checking for null because if the user does not enter anything, the value will be null
            result = theCode.startsWith(coursePrefix); // The startsWith() method checks whether a string starts with the specified character(s). startsWith method is case-sensitive.
        } else {
            result = true;
        }
        return result;
    }

}
