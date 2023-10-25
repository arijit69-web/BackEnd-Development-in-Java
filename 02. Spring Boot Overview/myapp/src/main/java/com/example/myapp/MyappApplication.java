package com.example.myapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyappApplication {

// Run this file as a java application and not as a server because Spring Boot includes it's own embedded server Tomcat

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args); // Bootstrap the Spring Boot App and give the class name 'MyappApplication.class' that we actually want to run

	}

}

/*

Spring Boot: Run from Command-Line:

The mvnw and mvnw.cmd files are also known as maven wrappers. These files let you run maven builds without installing a maven distribution on your machine.
Instead of installing many versions of Maven in your operating system, we can just use the project-specific Maven Wrapper script.

Option 1:

	Command:

		`mvnw package` : This will create a .jar file for our application inside the 'target' sub-directory.

		`cd target` : This will change the current directory | Move to 'target' sub-directory.

		`java -jar <filename.jar>` : This will run the app @ PORT 8080

Option 2:

	Command:

		`mvnw package` : This will create a .jar file for our application inside the target sub-directory.

		`mvnw spring-boot:run` : This will run the app @ PORT 8080

*/