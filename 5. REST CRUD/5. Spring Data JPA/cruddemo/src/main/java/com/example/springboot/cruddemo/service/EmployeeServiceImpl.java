package com.example.springboot.cruddemo.service;

import com.example.springboot.cruddemo.dao.EmployeeRepository;
import com.example.springboot.cruddemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service // @Service annotation is used with classes that provide some business functionalities.
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {// Constructor Injection
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer theId) {
        /*
        Optional is a new type introduced in Java 8. It is used to represent a value that may or may not be present. In other words, an Optional object can either contain a non-null value (in which case it is considered present) or it can contain no value at all (in which case it is considered empty).
        */
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new RuntimeException("Employee ID Not Found");
        }
        return theEmployee;
    }

    // @Transactional : Remove it since JPARepository provides this functionality
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    // @Transactional : Remove it since JPARepository provides this functionality
    @Override
    public void deleteById(Integer theId) {
        employeeRepository.deleteById(theId);

    }
}
