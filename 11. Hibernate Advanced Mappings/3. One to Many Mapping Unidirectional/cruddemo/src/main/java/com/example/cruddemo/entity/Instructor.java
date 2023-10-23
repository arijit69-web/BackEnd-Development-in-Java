package com.example.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    The @JoinColumn annotation in Hibernate is used to specify the mapping of a foreign key column in a relationship between two entities. The @JoinColumn annotation is applied on the owning side of the association to define the foreign key column name and other attributes which are related to the join column.
    */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    /* Only loads the instructor. Does not load the courses since they are LAZY loaded by default. FetchType for @OneToMany defaults to LAZY
    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})// Explicitly setting the fetch type as 'FetchType.EAGER' |  Refers to "instructor" property/field in "Course" class
    private List<Course> courses; // Do not CASCADE the deletes. If we delete a Instructor, do not delete the associated Course
    */
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Course> courses;

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Add convenience methods for Bi-Directional relationship
    /*
    Why this convenience methods?
    The convenience method add(Course tempCourse) in the Instructor class is used to simplify the process of adding a course to an instructor and, more importantly, to manage the bidirectional relationship between the Instructor and Course entities in a more structured way. Let me explain its use and compare it to how the code would look without such a convenience method:
    CASE 1: With Convenience Method (add(Course tempCourse)):
        ```
        Instructor instructor = new Instructor("John", "Doe", "johndoe@luv2code.com");
        Course course = new Course("Introduction to Programming");
        instructor.add(course); // Automatically establishes the relationship
        ```
    In this case, you simply call instructor.add(course) to add the course to the instructor's list of courses. This method internally takes care of setting the instructor for the course, ensuring that both sides of the bidirectional relationship are properly maintained. It makes the code more readable and less error-prone.
    CASE 2: Without Convenience Method:
    Without the convenience method, you would need to manually manage the bidirectional relationship, which could lead to more verbose and error-prone code:
        ```
        Instructor instructor = new Instructor("John", "Doe", "johndoe@example.com");
        Course course = new Course("Introduction to Programming");
        if (instructor.getCourses() == null) {
            instructor.setCourses(new ArrayList<>());
        }
        instructor.getCourses().add(course); // Manually add the course
        course.setInstructor(instructor); // Manually set the instructor for the course
        ```
    In this case, you have to check if the courses list is null, initialize it if necessary, manually add the course to the list, and also set the instructor for the course. This approach is more error-prone, less readable, and can lead to potential bugs if you forget to set one side of the relationship.
    The convenience method simplifies the process of adding courses and helps ensure that the bidirectional relationship is correctly maintained without requiring the developer to perform these tasks manually. It improves code readability and reduces the chances of introducing bugs related to the relationship management.
    */
    public void add(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);
        tempCourse.setInstructor(this);
    }
}
