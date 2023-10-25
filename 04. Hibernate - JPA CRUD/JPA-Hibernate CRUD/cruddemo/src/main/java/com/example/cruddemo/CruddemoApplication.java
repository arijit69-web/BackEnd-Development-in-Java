package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    // CommandLineRunner is from the Spring Boot framework. This little snippet of code here will be executed after the Spring beans have been loaded.
    @Bean
    // When using @Bean for a method such as commandLineRunner, Spring will check the arguments to the method. Spring will resolve the method arguments by injecting the appropriate Spring Bean. In this case, there is no need to use @Autowired. Spring will inject the beans automatically behind the scenes. | As per requirement, @Bean annotations inject objects from arguments as well
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) { // This will be executed after the Spring Beans have been loaded | Injecting StudentDAO | We are just giving reference to the StudentDAO and Spring will inject it accordingly
        return runner -> {

            // createStudent(studentDAO);

            // createMultipleStudent(studentDAO);

            // readStudent(studentDAO);

            // findAllStudent(studentDAO);

            // findByLastName(studentDAO);

            // updateStudent(studentDAO);

            // deleteStudent(studentDAO);

            // deleteAllStudent(studentDAO);


        }; // Java Lambda Expression

    }

    private void deleteAllStudent(StudentDAO studentDAO) {
        int numOfRowsDeleted = studentDAO.deleteAll();
        System.out.println("Number of Rows Deleted: " + numOfRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {

        int studentId = 1;
        studentDAO.delete(studentId);
        System.out.println("Student Deleted");
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 1;
        System.out.println("Student: " + studentId);
        Student myStudent = studentDAO.findById(studentId); // Fetch the student object by studentId and store it inside myStudent variable
        myStudent.setFirstName("Arijit"); // Change/Set the first name of the myStudent object
        studentDAO.update(myStudent); // Update the Student Object
        System.out.println("Updated Student: " + myStudent);

    }

    private void findByLastName(StudentDAO studentDAO) {

        String lastName = "Sarkar";
        List<Student> allStudent = studentDAO.findBylastName(lastName);
        for (Student singleStudent : allStudent) {
            System.out.println(singleStudent);
        }

    }

    private void findAllStudent(StudentDAO studentDAO) {
        List<Student> allStudent = studentDAO.findAll();
        for (Student singleStudent : allStudent) {
            System.out.println(singleStudent);
        }


    }

    private void readStudent(StudentDAO studentDAO) {

        int studentId = 3;
        Student myStudent = studentDAO.findById(studentId);
        System.out.println("Student: " + myStudent);
    }

    private void createMultipleStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating New Student");
        Student tempStudent1 = new Student("Mayank", "Sharma", "mayanksharma@gmail.com");
        Student tempStudent2 = new Student("Satyansh", "Gill", "satyanshgill@gmail.com");
        Student tempStudent3 = new Student("Pradipta", "Debsarma", "debsarmapradipta@gmail.com");

        // save the student object
        System.out.println("Saving the Student");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);


        // display id of the saved student
        System.out.println("Data Saved. Generated id: " + tempStudent1.getId());
        System.out.println("Data Saved. Generated id: " + tempStudent2.getId());
        System.out.println("Data Saved. Generated id: " + tempStudent3.getId());


    }

    private void createStudent(StudentDAO studentDAO) {

        // create the student object
        System.out.println("Creating New Student");
        Student tempStudent = new Student("Riya", "Chauhan", "riya1810chauhan@gmail.com");

        // save the student object
        System.out.println("Saving the Student");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Data Saved. Generated id: " + tempStudent.getId());

    }
}
