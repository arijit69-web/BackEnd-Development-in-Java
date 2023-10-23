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
    @JoinColumn(name = "instructor_id")// "instructor_id": This part of the annotation specifies the name of the foreign key column in the database table that links the entity with another related entity. In this case, it's specifying that the foreign key column in the database is named "instructor_id."
    private Instructor instructor;// Do not CASCADE the deletes. If we delete a Course, do not delete the associated Instructor

    // Unidirectional One to Many Mapping
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // CascadeType.ALL -> If we delete a course, also delete the reviews because reviews without a course have no meaning
    @JoinColumn(name = "course_id")
    private List<Review> reviews; // course_id column is not present in the Course table but present in the Review table. The hibernate knows how to join Course table's id with Review's course_id. So hibernate knows or have a mechanism to understand which entity or table the column name mentioned in @JoinColumn(name="column_name") belongs to and how to join. Hibernate will do for us based on our annotation.


    public Course() {

    }

    public Course(String title) {
        this.title = title;
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

    public void addReview(Review theReview)
    {
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
