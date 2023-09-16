package com.example.demo.rest;

import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    // Spring calls the methods annotated with @PostConstruct only once, just after the initialization of bean properties
    public void loadData() {

        theStudents = new ArrayList<>();
        // Jackson will convert List<Student> to JSON Array internally
        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));

    }


    @GetMapping("/students")
    public List<Student> getStudents() {

        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getSingleStudent(@PathVariable int studentId) { // The @PathVariable annotation is used to extract the value from the URL

        // check the studentID against the list size
        if ((studentId >= theStudents.size()) || studentId < 0) {
            throw new StudentNotFoundException("Student ID Not Found"); // New Exception is thrown
        }
        return theStudents.get(studentId);
    }

    // Add an Exception Handler to handle the above thrown Exception
    @ExceptionHandler
    // The @ExceptionHandler is an annotation used to handle the specific exceptions and sending the custom responses to the client.
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) // StudentErrorResponse : Type of the Response Body | StudentNotFoundException : Exception Type to Handle
    {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());


        // return a ResponseEntity

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); //  ResponseEntity represents the whole HTTP response: status code, headers, and body.

    }

    // Add Another Exception Handler to catch any type of Exception thrown
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





