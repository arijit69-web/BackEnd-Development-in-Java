package com.example.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
// "instructor_id": This part of the annotation specifies the name of the foreign key column in the database table that links the entity with another related entity. In this case, it's specifying that the foreign key column in the database is named "instructor_id."
    private Instructor instructor;// Do not CASCADE the deletes. If we delete a Course, do not delete the associated Instructor

    // Unidirectional One to Many Mapping
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // CascadeType.ALL -> If we delete a course, also delete the reviews because reviews without a course have no meaning
    @JoinColumn(name = "course_id")
    private List<Review> reviews; // course_id column is not present in the Course table but present in the Review table. The hibernate knows how to join Course table's id with Review's course_id. So hibernate knows or have a mechanism to understand which entity or table the column name mentioned in @JoinColumn(name="column_name") belongs to and how to join. Hibernate will do for us based on our annotation.

    /*

    @JoinTable: The @JoinTable annotation is used to define the configuration for a join table that represents a many-to-many relationship between two entities. In this case, the many-to-many relationship is between the entity containing this code (let's call it "Course") and the Student entity.

    name = "course_student": The name attribute specifies the name of the join table that will be created in the database to manage the many-to-many relationship between "Course" and "Student." In this case, the join table will be named "course_student."

    joinColumns = @JoinColumn(name = "course_id"): The joinColumns attribute is used to specify the mapping of columns in the join table to the columns in the owning entity ("Course" in this case). This line specifies that the "course_id" column in the join table corresponds to the primary key of the "Course" entity. This effectively establishes a foreign key relationship between the join table and the "Course" entity, connecting courses to students.

    inverseJoinColumns = @JoinColumn(name = "student_id"): The inverseJoinColumns attribute is used to specify the mapping of columns in the join table to the columns in the target entity ("Student" in this case). This line specifies that the "student_id" column in the join table corresponds to the primary key of the "Student" entity. This effectively establishes a foreign key relationship between the join table and the "Student" entity, connecting students to courses.

    */

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id") // In the context of a many-to-many relationship, "inverseJoinColumns" typically refers to the columns in the target entity, which is the entity on the opposite side of the relationship.
    )
    private List<Student> students;


    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    // add a convenience method

    public void addStudent(Student theStudent) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(theStudent);
    }

    public void addReview(Review theReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        reviews.add(theReview);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
