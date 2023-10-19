package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
            entityManager.remove(tempInstructor); // This will also delete the InstructorDetails object because of CascadeType.ALL
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
}
