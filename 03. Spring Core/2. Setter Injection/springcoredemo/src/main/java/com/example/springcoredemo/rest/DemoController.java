package com.example.springcoredemo.rest;

import com.example.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define the private field for the dependency
    private Coach myCoach;

    /*
    @Autowired
    public void setCoach(Coach theCoach)
    {
        myCoach = theCoach;
    }
    */
    @Autowired
    public void doSomeStuff(Coach theCoach) // Inject dependencies by calling any method name on your class instead of `setCoach()` | Simply give: @Autowired Annotation
    {
        myCoach = theCoach;
    }


    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();


    }


}
/*
Behind the scene the Spring Framework will perform operations:
// Setter Injection

Coach theCoach = new CricketCoach(); // Spring will create a new instance of your coach, new CricketCoach
DemoController demoController = new DemoController(theCoach); // It'll also perform constructor injection with your demo controller | It'll actually inject theCoach into the demo controller
demoController.setCoach(theCoach)

The whole idea of injection is that there's a dependency or a helper. So in this case, theCoach is a dependency or a helper
*/
