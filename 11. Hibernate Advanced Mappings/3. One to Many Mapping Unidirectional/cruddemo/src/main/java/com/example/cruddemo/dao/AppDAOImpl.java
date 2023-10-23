package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor); // This will also save the InstructorDetails object because of CASCADE Type.ALL

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);// This will also retrieve the InstructorDetails object because of default behavior of @OneToOne mapping and fetch type is eager
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        if (tempInstructor == null) {

            System.out.println("There is no instructor for ID " + theId);

        } else {

            List<Course> courses = tempInstructor.getCourses();
            // Break the associations of all the courses for the current instructor that you want to remove
            for (Course tempCourse : courses) {
                tempCourse.setInstructor(null);
            }
            entityManager.remove(tempInstructor); // We only delete the instructor not the associated courses based on our cascade types.
            // entityManager.remove(tempInstructor); // This will also delete the InstructorDetails object because of CascadeType.ALL
            System.out.println("Instructor deleted successfully!");
        }
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {

        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        if (tempInstructorDetail == null) {

            System.out.println("There is no instructor for ID " + theId);

        } else {
            entityManager.remove(tempInstructorDetail);
            /*
            `tempInstructorDetail.getInstructor().setInstructorDetail(null);`

            Code Explanation:
            When you use entityManager.remove(tempInstructorDetail), it schedules the removal of the tempInstructorDetail entity for deletion, and due to the cascade configuration, the associated Instructor entity is also scheduled for removal (which is often not the desired behavior, but it depends on your specific use case).
            In a bidirectional relationship, you have two sides, in this case, an Instructor and an InstructorDetail, that reference each other. When you delete the tempInstructorDetail, the reference to this InstructorDetail is still maintained by the Instructor. This can lead to a situation where the InstructorDetail is removed from the database, but the Instructor still holds a reference to it.
            By calling tempInstructorDetail.getInstructor().setInstructorDetail(null), you are breaking the association between the Instructor and the InstructorDetail. This ensures that the Instructor no longer references the deleted InstructorDetail. This is important for maintaining database integrity because it effectively removes the association between the two entities.
            Without this line, the Instructor would still reference the deleted InstructorDetail, and it would not be properly disassociated from the deleted entity. This could lead to data inconsistencies and issues when working with the data in your database.
            */
            tempInstructorDetail.getInstructor().setInstructorDetail(null);// Remove the associated object reference | Break the Bi-directional link b/w Instructor & InstructorDetails
            System.out.println("Instructor deleted successfully!");
        }
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // Create Custom Query

        /* Why we need to write instructor.id in query instead of instructor_id ?
            When using JPA Query Language (JPQL), we always reference the entity class name and the entity fields. The entity field is instructor.id (case-sensitive) - instructor is the name of the field in the Course class Java code.  We use the dot-notation, to access another field within the class.
            JPQL does not use the actual database table or column names, instead it uses the Java entity class names and entity fields.
        */
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id= :data", Course.class
        );
        query.setParameter("data", theId);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorWithCoursesUsingJoinFetch(int theId) {
        // Fetching Instructor & Courses in a single query and also keep LAZY option available

        /*
        The provided code appears to be a JPQL (Java Persistence Query Language) query used in the context of Java Persistence API (JPA), typically for querying a relational database through an object-relational mapping (ORM) framework such as Hibernate. Let's break down the query step by step and explain its purpose:

            SELECT i FROM Instructor i: This part of the query selects all the Instructor objects (often represented as Java objects) from the database and assigns an alias i to the Instructor entity.

            JOIN FETCH i.courses: This part specifies a fetch join to eagerly load the courses collection associated with each instructor. In other words, it tells the query to retrieve both instructor and their associated courses in a single query, which can help avoid the N+1 query problem where a separate query is executed for each instructor's courses.

            WHERE i.id = :data: This clause filters the results to include only the Instructor with a specific id value, which is bound to a parameter named data. The :data placeholder is likely to be replaced with an actual value during query execution.

        In summary, this query retrieves an Instructor and their associated courses based on a specified id. The use of a fetch join ensures that the associated courses are eagerly loaded with the instructor, minimizing the number of database queries required.
        */
        TypedQuery<Instructor> query = entityManager.createQuery(// i - is an alias shortname for Instructor
                "select i from Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class); //Even with Instructor @OneToMany(fetchType=LAZY) This query will still retrieve Instructor & Courses | The JOIN FETCH is similar to EAGER loading

        // Fetching Instructor, Instructor Details & Courses in a single query and also keep LAZY option available : "select i from Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail where i.id = :data"
        query.setParameter("data", theId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor); // merge() will update an existing entity

    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course tempCourse = entityManager.find(Course.class, theId);
        entityManager.remove(tempCourse);

    }
}
