package com.example.springdemo.mvc;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/*

Both model.addAttribute and @ModelAttribute are used to pass data from the server-side (Java code) to the view (HTML or template files). However, they serve different purposes and are used in different contexts.

1. model.addAttribute: model.addAttribute is a method provided by the Model interface in Spring MVC. It is used within controller methods to add attributes to the model. The model is a container that holds data to be passed to the view. When you add attributes using model.addAttribute, they are available to be accessed in the view template using expressions like ${attributeName}.

Here's an example of how to use model.addAttribute:

@Controller
public class MyController {

    @RequestMapping("/example")
    public String exampleMethod(Model model) {
        String message = "Hello, World!";
        model.addAttribute("message", message);
        return "exampleView";
    }
}
In the above example, the exampleMethod adds the "message" attribute to the model, and the value of this attribute will be accessible in the "exampleView" template.



2. @ModelAttribute: @ModelAttribute is an annotation in Spring MVC that can be used in two different ways:

a. Binding Form Data: When used as a method parameter in a controller method, @ModelAttribute binds form data submitted by the client to a Java object. It helps to automatically populate the model attribute from the form data.

Example:

@Controller
public class MyController {

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute MyFormObject formObject) {
        // Process form data
        // ...
        return "resultView";
    }
}
b. Adding Attributes to Model: When used as a method-level annotation, @ModelAttribute defines a method that adds one or more model attributes that will be included in the model for all handler methods within the same controller class.

Example:

@Controller
public class MyController {

    @ModelAttribute("message")
    public String addMessageToModel() {
        return "Hello, World!";
    }

    @RequestMapping("/example")
    public String exampleMethod() {
        return "exampleView";
    }
}
In this example, the addMessageToModel method adds the "message" attribute to the model, and it will be available in all handler methods within the same controller.

So, in summary:

model.addAttribute is used to add attributes to the model within a specific handler method.

@ModelAttribute is used either to bind form data to a Java object or to define a method that adds attributes to the model for all handler methods within the same controller class.

*/

@Controller
public class CustomerController {
    @InitBinder // @InitBinder annotation works as a pre-processor | It will pre-process each web request to our controller
    public void initBinder(WebDataBinder dataBinder) {

        // StringTrimmerEditor: removes whitespace - leading & trailing
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true means trim empty string to null
        // For every String class, apply the StringTrimmerEditor |  So in a nutshell, what this does is, it'll pre-process every String from the form data. It'll remove the leading and trailing white space. And again, if the String only has white space, it'll trim it down to null.
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel) { // Model allows us to share information between Controllers and View pages
        theModel.addAttribute("customer", new Customer()); // Creating an  empty Customer() instance
        return "customer-form";
    }
    /*
    @Valid: @Valid is an annotation used to indicate that the annotated parameter should be validated using a validation framework, typically in combination with Jakarta Bean Validation annotations. This is particularly useful when you want to ensure that the data submitted by the client adheres to certain validation rules defined on your data models.
    For example, if you have a form that submits user data, and you want to ensure that the submitted data is valid (e.g., required fields are filled, email format is correct, etc.), you can apply @Valid to the parameter that represents the form object. This annotation triggers the validation process, and any validation errors are collected and can be accessed later.

    @ModelAttribute: @ModelAttribute is an annotation used to indicate that the annotated parameter should be bound to a model attribute in the request, typically from form fields. It's commonly used to extract data from the incoming request and populate a Java object that will be used in the controller's method for further processing.
    For instance, when a form is submitted with various fields, you can use @ModelAttribute to bind the form data to a Java object. The attribute name can be explicitly specified using the annotation, or the default naming based on the object's class name will be used.
    In summary, @Valid is used for triggering validation of annotated parameters, and @ModelAttribute is used for binding incoming data to a Java object that can be used within a controller method. They serve different purposes in the Spring MVC framework.
    */

    @PostMapping("/processForm")
    public String processForm( // @Valid: Tell Spring MVC to perform validation and it'll use those validation rules that's been defined in that customer class.
                               @Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {// And theBindingResult actually holds the results of the validation. So Spring MVC will go through and perform all the validation, get the results, if there were any errors, what the error messages were. So on and so forth. If everything was successful, then it'll have that data in this given object here, theCustomer.
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }

}
