package com.example.demo.rest;

import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct   // Spring calls the methods annotated with @PostConstruct only once, just after the initialization of bean properties
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

        return theStudents.get(studentId);
    }
}
