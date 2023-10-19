package com.example.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /*
    The code you provided appears to be written in Java and is related to object-relational mapping (ORM) using JPA (Java Persistence API), which is commonly used to interact with relational databases. It seems to define a relationship between two entities using JPA annotations.
    @OneToOne(cascade = CascadeType.ALL): This is a JPA annotation that indicates a one-to-one relationship between two entities. In the context of your code, it suggests that an instance of the entity containing this annotation (Instructor) is associated with one instance of another entity (InstructorDetail). The cascade = CascadeType.ALL attribute means that any changes (e.g., insert, update, delete) made to the Instructor entity should be cascaded to the associated InstructorDetail entity. This ensures that when you modify an Instructor, it will also affect the related InstructorDetail.
    @JoinColumn(name = "instructor_detail_id"): This annotation is used to specify the name of the column in the database table for the foreign key that links the Instructor entity to the InstructorDetail entity. In this case, it indicates that the Instructor entity has a foreign key column named "instructor_detail_id" that connects it to the InstructorDetail entity.
    */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
