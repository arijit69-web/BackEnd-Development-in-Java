package com.example.springboot.cruddemo.exception;

import com.example.springboot.cruddemo.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler
    // The @ExceptionHandler is an annotation used to handle the specific exceptions and sending the custom responses to the client.
    public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc) {

        EmployeeErrorResponse error = new EmployeeErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        // return a ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); //  ResponseEntity represents the whole HTTP response: status code, headers, and body.

    }
}


