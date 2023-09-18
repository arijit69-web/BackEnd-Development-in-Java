package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository is a JPA (Java Persistence API) specific extension of Repository. It contains the full API of CrudRepository and PagingAndSortingRepository. So it contains API for basic CRUD operations and also API for pagination and sorting.
// Spring Data JPA provides the interface: JpaRepository | Create a Spring Data JPA DAO and just plug in your Entity<Employee> type and primary key<Integer> | No need for implementation class | Spring will give you a REST CRUD implementation for FREE like MAGIC
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
