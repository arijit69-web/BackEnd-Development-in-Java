package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;


    @Autowired
    // The @Autowired annotation is used to inject the EntityManager bean [automatically created by Spring Boot] into the class. In Spring, dependency injection is a way to provide objects that a class depends on (in this case, the EntityManager) from an external source (in this case, the Spring application context)
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class); // JPA provides a special Query sub-type known as a TypedQuery. This is always preferred if we know our Query result type beforehand. Additionally, it makes our code much more reliable and easier to test. | Name of JPA Entity, the class name
        List<Employee> employees = theQuery.getResultList();
        return employees;
    }

    @Override
    public Employee findById(Integer theId) {
        return entityManager.find(Employee.class, theId);
    }

    // We don’t use @Transactional at DAO layer. It will be handled at Service layer | Best practice is to apply transactional boundaries at the Service Layer
    @Override
    public Employee save(Employee theEmployee) {
        return entityManager.merge(theEmployee); // merge method save/update the value depending on the ID of the entity. | It returns the new ID from the DB in the case of insert | If we pass the ID in the entity and if it is present inside the DB then it will update the data or else it will create a new data.
    }

    @Override
    public void deleteById(Integer theId) {
        // retrieve theStudent object
        Employee theEmployee = entityManager.find(Employee.class, theId);
        // delete theStudent object from the DB
        entityManager.remove(theEmployee);

    }
}
