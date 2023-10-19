package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
            // findInstructorDetail(appDAO);
             deleteInstructorDetail(appDAO);

        };
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId = 11;
        appDAO.deleteInstructorDetailsById(theId);

    }

    private void findInstructorDetail(AppDAO appDAO) {
        int theId = 10;
        InstructorDetail tempInstructorDetails = appDAO.findInstructorDetailById(theId);
        if (tempInstructorDetails == null)
            System.out.println("There is no instructor for ID " + theId);
        else {
            System.out.println("Instructor Details: " + tempInstructorDetails);
            System.out.println("Instructor: " + tempInstructorDetails.getInstructor());
        }
    }

    private void deleteInstructor(AppDAO appDAO) {
        int theId = 7;
        appDAO.deleteInstructorById(theId);

    }

    private void findInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        if (tempInstructor == null)
            System.out.println("There is no instructor for ID " + theId);
        else {
            System.out.println("Instructor: " + tempInstructor);
            System.out.println("Instructor Details: " + tempInstructor.getInstructorDetail());
        }
    }

    private void createInstructor(AppDAO appDAO) {

        Instructor tempInstructor = new Instructor("Riya", "Chauhan", "rits1810@gmail.com");
        InstructorDetail tempInstructorDetail = new InstructorDetail("thevlogger", "Vlogging");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("Saved Data: " + tempInstructor);

        appDAO.save(tempInstructor);// It inserts the associated entity first i.e. InstructorDetail and then inserts the Instructor | Due to relationship of the foreign key Instructor needs to know the id of the InstructorDetail
    }
}

