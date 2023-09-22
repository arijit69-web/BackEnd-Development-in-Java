package com.example.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*

theModel is a method parameter of type Model. It is used to pass data from the controller to the view in a Spring MVC application.

In Spring MVC, the Model interface is commonly used to store and manage attributes that need to be made available to the view. When you add attributes to the Model object, they can be accessed by the view template to dynamically generate content for the web page.

In the code snippet, the following is happening:

1. An attribute named "theDate" is added to the theModel object. This attribute has a value of a new java.util.Date() object, which represents the current date and time.

2. The method returns a string, "helloworld," which is typically the logical view name. This view name is used to resolve to an actual view template (e.g., a Thymeleaf, JSP, or HTML template) that will be rendered to the client.

So, theModel parameter serves as a way to pass data from the controller to the view, allowing you to populate the view template with dynamic data that can be displayed to the user when the web page is generated.

*/
@Controller
public class DemoController {
    @GetMapping("/hello")
    public String sayHello(Model theModel) { // Model is an interface in Spring Framework that represents the model in the MVC pattern. It is used to pass data from the controller to the view.
        // adding an attribute to the Model
        theModel.addAttribute("theDate", new java.util.Date()); // In a web app, Thymeleaf is processed on the Server-side.
        return "helloworld"; // Due to the Thymeleaf dependency in the Maven POM file, Spring Boot will automatically configure itself to use it. As a result, when we return helloworld, it will look for the helloworld.html file in src/main/resources/templates path.
    }
}
