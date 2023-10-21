package com.example.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "InstructorDetail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    // Bi-Directional : One-to-One Mapping
    /*
    By setting mappedBy="instructorDetail", you are specifying that the Instructor entity is the owner of the relationship. This means that the Instructor table will have a foreign key column [ named "instructorDetail" ] that links to the primary key [ named "id" ] of the InstructorDetail table and avoids creating redundant columns.
    This indicates a bidirectional one-to-one relationship. In addition to the Instructor owning the relationship, you should have a corresponding field in the InstructorDetail entity that establishes the relationship from the other side.
    In summary, mappedBy is used to establish a bidirectional relationship and indicates which entity is the owner of the relationship. It is typically used when you want to manage a one-to-one or one-to-many relationship between entities while having one side of the relationship as the owner of the foreign key that links the entities in the database.
    */
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH} ) // cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}: Choose everything except REMOVE | This will allow us to delete an InstructorDetail, but not cascade the delete operation or the remove operation to the given Instructor
    // @OneToOne(mappedBy="instructorDetail", cascade=CascadeType.ALL) // Refers to “instructorDetail” property in “Instructor” class | Hibernate use this information from the Instructor class @JoinColumn() and find the associated Instructor
    private Instructor instructor;

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public InstructorDetail() {

    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
