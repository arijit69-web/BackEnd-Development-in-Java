package com.example.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "comment")
    private String comment;

    /*
    How course_id get assigned ?

    Under the Hood:

    When you save tempCourse which contains reviews using appDAO.save(tempCourse);, JPA looks at the cascade setting and sees that it needs to also handle the associated reviews.
    JPA then goes on to persist each Review associated with the Course. Since the Review table has a course_id column, JPA knows that it should set this column with the ID of the Course entity that's being saved.

    # Now, you might ask how come we don't have a property for course_id in Review entity? Good question.
    Since the course_id is just a mechanism to establish a relationship in a relational database and you've already established that relationship in your Java code with the @OneToMany and @JoinColumn annotations, there's no need to also have a course_id property in the Review entity. JPA abstracts it away.

    # Now, Why course_id gets set:
    The magic mainly lies in the @JoinColumn(name = "course_id") annotation. This tells JPA that the relationship between Course and Review is managed by the course_id column in the Review table. This is why JPA knows where to set the ID.
    During the persist operation, since Course is the owning side of the relationship (meaning it determines how the relationship gets stored in the database), JPA will insert records into the Course table and then insert related records into the Review table with the course_id set.
    */
    public Review() {

    }

    public Review(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
