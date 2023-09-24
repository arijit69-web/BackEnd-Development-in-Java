package com.example.springdemo.mvc;

import com.example.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName = "";

    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    @NotNull(message = "is required")
    private Integer freePasses; // Why 'Integer' & not 'int'? Difficulty in Type Conversion  Primitive types do not have the ability to be null, which will often be the case with freePasses values [when freePasses field is empty/null], hence we are using Wrapper class Integer that has the ability to be NULL [Easy Type Conversion].

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode;

    // @CourseCode -> The value will be 'LUV' & the message will be 'must start with LUV' by default
    @CourseCode(value = "SRKR", message = "must start with SRKR") // Our custom annotation
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
