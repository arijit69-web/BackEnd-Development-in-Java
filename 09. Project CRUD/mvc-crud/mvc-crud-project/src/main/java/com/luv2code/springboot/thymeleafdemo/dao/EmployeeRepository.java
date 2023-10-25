package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // add  a method to sort by firstName
    public List<Employee> findAllByOrderByFirstNameAsc(); //  Spring Data JPA, will parse the method name. They'll look for a specific format and pattern [find all by & order by first name ascending], and they'll create the appropriate query for you behind the scenes just like magic. | SQL Query: "from Employee order by firstName asc"
}
