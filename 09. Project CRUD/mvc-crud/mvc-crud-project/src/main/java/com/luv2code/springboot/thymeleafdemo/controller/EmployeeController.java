package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService; // Set up constructor for constructor injection
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee); // Our Thymeleaf template will access this data for the binding form data
        return "employees/employee-form";
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeService.findAll();

        //  When form is loaded, it will call: employee.getFirstName() & employee.getLastName()
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        // save the employee in the DB
        employeeService.save(theEmployee); // When form is submitted, it will call: employee.setFirstName() & employee.setLastName()
        // Once the form data has been saved, the user will be redirected to a list of all the employees
        return "redirect:/employees/list"; // Redirect to GET Request Mapping: '/employees/list' | Post/Redirect/Get (PRG) design pattern
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {
        // Fetch the Employee using the ID
        Employee theEmployee = employeeService.findById(theId);
        // Set the Employee as a Model Attribute to pre-populate the Form
        theModel.addAttribute("employee", theEmployee);
        // When form is loaded, it will call: employee.getFirstName() & employee.getLastName() | Form is pre-populated using getters
        return "employees/employee-form";
    }

    // When using the default behavior of HTML forms, the HTML forms only support GET and POST. When building Spring REST APIs, you can make use of GET, PUT, POST, DELETE, etc. However, GET, POST, PUT and DELETE are supported by the implementations of XMLHttpRequest (i.e. AJAX calls) in all the major web browsers.
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {
        employeeService.deleteById(theId);
        return "redirect:/employees/list";
    }
}









