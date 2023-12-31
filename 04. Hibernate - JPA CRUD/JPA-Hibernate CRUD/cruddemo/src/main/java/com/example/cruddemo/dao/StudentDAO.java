package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Student;

import java.util.List;

// StudentDAO: Responsible for interfacing with the database | This is a common design pattern: Data Access Object (DAO)

/*
The Data Access Object (DAO) pattern is a structural pattern that allows us to isolate the application/business layer from the persistence layer (usually a relational database but could be any other persistence mechanism) using an abstract API.
The API hides from the application all the complexity of performing CRUD operations in the underlying storage mechanism. This permits both layers to evolve separately without knowing anything about each other.
A simple DAO class will take care of keeping these components neatly decoupled from each other.
The DAO (Data Access Object) layer is crucial in separating the database access logic from the business logic in the application.
This architectural pattern provides a clean and modular way to interact with the database, This means that the DAO layer hides the details
of how data is stored and retrieved, making it easier for other parts of the application to access data, improving code maintainability and testability.
LINK: [DAO DESIGN PATTERN] https://www.digitalocean.com/community/tutorials/dao-design-pattern
*/
public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findBylastName(String theLastName);

    void update(Student theStudent);

    void delete (Integer id);

    int deleteAll();


}
