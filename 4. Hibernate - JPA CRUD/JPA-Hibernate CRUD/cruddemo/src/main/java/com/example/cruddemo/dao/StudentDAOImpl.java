package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Spring has this @Repository annotation that's basically a sub-annotation of @Component
@Repository: For annotating DAOs, Translates those Checked JDBC exceptions[Java verifies checked exceptions at compile-time] into Unchecked JDBC exceptions[Java does not verify unchecked exceptions at compile-time], & supports Component Scanning.
*/
@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for Entity Manager
    private EntityManager entityManager; // DAO needs a JPA Entity Manager | JPA Entity Manager is the main component for saving/retrieving entities/data.


    // inject entity manager using constructor

    @Autowired
    // The @Autowired annotation is used to inject the EntityManager bean into the class. In Spring, dependency injection is a way to provide objects that a class depends on (in this case, the EntityManager) from an external source (in this case, the Spring application context)
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    // implement save method
    @Transactional
    // This annotation can be applied to a method or a class to indicate that the method or all methods in the class should be executed within a transaction.
    @Override
    public void save(Student theStudent) {

        entityManager.persist(theStudent); // saves the student into the DB


    }

    // No need to add @Transactional since we are doing a search query
    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); // Student.class => Entity class & id => Primary Key | If not found, returns null

    }

    @Override
    public List<Student> findAll() {
        // create query
        // Note: Student is NOT the name of the Database table | All JPQL syntax is based on entity name and entity fields
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student order by lastName asc", Student.class); // JPA provides a special Query sub-type known as a TypedQuery. This is always preferred if we know our Query result type beforehand. Additionally, it makes our code much more reliable and easier to test. | Name of JPA Entity, the class name
        List<Student> students = theQuery.getResultList();
        return students;
    }

    @Override
    public List<Student> findBylastName(String theLastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class); // JPQL Named Parameters are prefixed with a colon : | Think of this as a placeholder that is filled in later
        theQuery.setParameter("theData", theLastName);
        List<Student> students = theQuery.getResultList();
        return students;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {

        entityManager.merge(theStudent); // Update the new Student object inside the DB

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // retrieve theStudent object
        Student theStudent = entityManager.find(Student.class, id);
        // delete theStudent object from the DB
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;


    }
}


