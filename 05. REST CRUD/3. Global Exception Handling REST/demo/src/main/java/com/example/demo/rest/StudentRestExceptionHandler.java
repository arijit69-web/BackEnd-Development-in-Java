package com.example.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Global Exception Handling - Best Prcatice
@ControllerAdvice
//  @ControllerAdvice is a specialized form of the spring's stereotype annotation which allows handling exceptions across the whole application in one global handling component. Think of this as an interceptor of exceptions thrown by methods annotated with @RequestMapping.
public class StudentRestExceptionHandler {
    @ExceptionHandler
    // The @ExceptionHandler is an annotation used to handle the specific exceptions and sending the custom responses to the client.
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) // StudentErrorResponse : Type of the Response Body | StudentNotFoundException : Exception Type to Handle
    {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        // return a ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //  ResponseEntity represents the whole HTTP response: status code, headers, and body.

    }
}
