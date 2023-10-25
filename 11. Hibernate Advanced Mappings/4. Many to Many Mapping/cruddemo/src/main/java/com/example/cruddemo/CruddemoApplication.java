package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
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

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            // createInstructor(appDAO);
            // findInstructor(appDAO);
            // deleteInstructor(appDAO);
            // findInstructorDetail(appDAO);
            // deleteInstructorDetail(appDAO);
            // createInstructorWithCourses(appDAO);
            // findInstructorWithCourses(appDAO);
            // findCoursesByInstructorId(appDAO);
            // findInstructorWithCoursesUsingJoinFetch(appDAO);// Fetching Instructor & Courses in a single query and also keep LAZY option available | //Even with Instructor @OneToMany(fetchType=LAZY) This query will still retrieve Instructor & Courses | The JOIN FETCH is similar to EAGER loading
            // updateInstructor(appDAO);
            // updateCourse(appDAO);
            // deleteCourseById(appDAO);
            // createCourseAndReviews(appDAO);
            // retrieveCourseAndReviews(appDAO);
            // deleteCourseAndReviews(appDAO);
            // createCourseAndStudents(appDAO);
            // findCourseAndStudents(appDAO);
            // addMoreCoursesForStudent(appDAO);
            deleteStudent(appDAO);





        };
    }

    private void deleteStudent(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Student Deleted!");
    }


    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Student: " + tempStudent);
        System.out.println("Associated Courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

    }

    private void findCourseAndStudents(AppDAO appDAO) {
        System.out.println( "------------------Find Student And Courses By Course Id--------------------");
        int theId = 11;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Associated Course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

        System.out.println( "------------------Find Student And Courses By Student Id--------------------");
        int theStudentId = 3;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theStudentId);
        System.out.println("Students: " + tempStudent);
        System.out.println("Associated Course: " + tempStudent.getCourses());
    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course tempCourse1 = new Course("Pacman - How To Score One Million Points");
        Course tempCourse2 = new Course("Max Payne");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        Student tempStudent3 = new Student("Arijit", "Sarkar", "arijitsrkr13@gmail.com");

        // Adding Student to the Course
        tempCourse1.addStudent(tempStudent1);
        tempCourse1.addStudent(tempStudent2);
        // appDAO.save(tempCourse1);
        // System.out.println("Course: " + tempStudent3);
        // System.out.println("Associated Students: " + tempStudent3.getCourses());

        // Adding Course to the Student
        tempStudent3.addCourse(tempCourse2);
        appDAO.save(tempStudent3);

        System.out.println("Student: " + tempStudent3);
        System.out.println("Associated Courses: " + tempStudent3.getCourses());


    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Deleting Course id: " + theId);
        appDAO.deleteCourseById(theId);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        System.out.println("Course: " + tempCourse);

        System.out.println("Reviews: " + tempCourse.getReviews());
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done."));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        appDAO.save(tempCourse); // We will save the Course and associated Reviews along with it because we have CascadeType.ALL


        System.out.println("Course: " + tempCourse);
        System.out.println("Reviews: " + tempCourse.getReviews());


    }

    private void deleteCourseById(AppDAO appDAO) {
        int theId = 13;
        appDAO.deleteCourseById(theId);
        System.out.println("Course Deleted!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 12;
        Course tempCourse = appDAO.findCourseById(theId);
        tempCourse.setTitle("I.G.I.: I'm Going In");
        appDAO.update(tempCourse);
        System.out.println("Updated Course: " + tempCourse);

    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        tempInstructor.setLastName("TESTER");
        appDAO.update(tempInstructor);
        System.out.println("Updated Instructor: " + tempInstructor);

    }

    private void findInstructorWithCoursesUsingJoinFetch(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorWithCoursesUsingJoinFetch(theId);
        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());
    }

    private void findCoursesByInstructorId(AppDAO appDAO) {
        int theId = 1;
        Instructor instructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor: " + instructor);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        instructor.setCourses(courses); // It can't fetch the course directly because the Fetch Type is LAZY, so we associate/insert the course object inside the instructor object
        System.out.println("Courses: " + instructor.getCourses());
        System.out.println("Courses: " + courses); // Using a custom SQL query, fetch course data from the course table with Instructor ID as theId
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("Instructor: " + tempInstructor);
        System.out.println("Courses: " + tempInstructor.getCourses());
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // Creating Instructor
        Instructor tempInstructor = new Instructor("Susan", "Stark", "susan134@gmail.com");
        // Creating Instructor Details
        InstructorDetail tempInstructorDetail = new InstructorDetail("thegammer", "Video Gaming");
        // In the Instructor Object, set the instructor details
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // Creating Courses
        Course course1 = new Course("PUBG");
        Course course2 = new Course("PinBall");
        Course course3 = new Course("IGI");

        // In the Instructor Object, set the courses
        tempInstructor.add(course1);
        tempInstructor.add(course2);
        tempInstructor.add(course3);


        System.out.println("Saved Data: " + tempInstructor);
        System.out.println("The Courses: " + tempInstructor.getCourses());

        // This will also save the Courses because of CascadeType.PERSIST in the Instructor entity
        appDAO.save(tempInstructor);// It inserts the associated entity first i.e. InstructorDetail and then inserts the Instructor | Due to relationship of the foreign key Instructor needs to know the id of the InstructorDetail

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
        int theId = 1;
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

