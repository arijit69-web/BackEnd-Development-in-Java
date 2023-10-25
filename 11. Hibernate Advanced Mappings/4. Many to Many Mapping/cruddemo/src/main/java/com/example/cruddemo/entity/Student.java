package com.example.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

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

    1.  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}):

        @ManyToMany is an annotation used to define a many-to-many relationship between two entities. In this case, it indicates that there is a many-to-many relationship between the entity containing this code and the Course entity.

        fetch = FetchType.LAZY specifies the fetch type for this relationship. In this case, it is set to "LAZY", which means that the associated Course entities will be loaded from the database only when they are accessed. This is often done to improve performance by avoiding unnecessary database queries when loading the parent entity (e.g., a student) without accessing its related courses.

        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH} defines the cascade operations that should be applied to the related Course entities when certain operations are performed on the parent entity (e.g., a student). The specified cascade types indicate that when a student entity is persisted (i.e., saved), merged (i.e., updated), detached (i.e., removed from the persistence context), or refreshed (i.e., reloaded from the database), the corresponding operations should also be applied to the associated Course entities.

    2.  @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id")):

        @JoinTable is used to specify the name of the join table that will be used to link the two sides of the many-to-many relationship (i.e., the Student entity and the Course entity). A join table is an auxiliary table in a relational database that helps manage many-to-many relationships.
        name = "course_student" specifies the name of the join table as "course_student."

        joinColumns and inverseJoinColumns specify how the columns in the join table should be mapped to the columns in the associated entities. In this case, it indicates that:
        -   joinColumns maps to the "student_id" column in the join table, which corresponds to the primary key of the Student entity.
        -   inverseJoinColumns maps to the "course_id" column in the join table, which corresponds to the primary key of the Course entity.

        The @JoinColumn annotation for the student_id column in the join table knows that it maps to the primary key of the Student entity because it is placed on the field or property in the Student entity that represents the primary key of that entity. In a typical JPA entity class, the primary key is annotated with @Id
        In this example, the @Id annotation is used to mark the id field as the primary key for the Student entity. The @JoinColumn(name = "student_id") annotation in your code specifies that the "student_id" column in the join table should be mapped to the id field in the Student entity, which is its primary key.

    */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id") // In the context of a many-to-many relationship, "inverseJoinColumns" typically refers to the columns in the target entity, which is the entity on the opposite side of the relationship.
    )
    private List<Course> courses;

    public Student() {

    }

    public Student(String firstName, String lastName, String email) {
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // add convenience method

    public void addCourse(Course theCourse) {

        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(theCourse);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}