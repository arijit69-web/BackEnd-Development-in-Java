package com.example.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// Spring @Configuration annotation is part of the spring core framework. Spring Configuration annotation indicates that the class has @Bean definition methods. So Spring container can process the class and generate Spring Beans to be used in the application. Spring @Configuration annotation allows us to use annotations for dependency injection.
public class DemoSecurityConfig {
    @Bean
    // When should you use @Bean? Sometimes automatic configuration is not an option. When? Let's imagine that you want to wire components from 3rd-party libraries (you don't have the source code so you can't annotate its classes with @Component), so automatic configuration is not possible. The @Bean annotation returns an object that spring should register as bean in application context. The body of the method bears the logic responsible for creating the instance.
    public InMemoryUserDetailsManager userDetailsManager() {// Spring In-Memory authentication uses InMemoryUserDetailsManager internally store and retrieve the user-related information which is required for Authentication.
        // So whenever the user requests for any details, the request is filtered and passed to AuthenticationManager, which tries to authenticate the request by the using UserDetailsService.
        // The UserDetailsService is responsible for retrieving the correct user details, InMemoryUserDetailsManager indirectly implements UserDetailsService interface. Now the InMemoryUserDetailsManager reads the in-memory hashmap and loads the UserDetails by calling the loadUserByUsername() method.
        // Once the UserDetails is loaded via InMemoryUserDetailsManager and the authentication is successful, the SecurityContext will be updated and the request will proceed.
        // Since we defined our users here. Spring Boot will NOT use the user/password from application.properties file instead it will use the userDetailsManager().
        UserDetails john = User.builder() // creates a UserBuilder along with username, password and roles and return UserBuilder
                .username("john")
                .password("{noop}test123")//  {noop} for plain text passwords
                .roles("EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    //  Modify Spring Security Configuration to reference custom login form
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // authorizeHttpRequests(): Restrict access based on the HTTP request
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .anyRequest().authenticated() // Any request to the app must be authenticated (ie logged in)
                )// We are customizing the login form reference
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage") // Show our custom login form at the request mapping: “/showMyLoginPage”
                                .loginProcessingUrl("/authenticateTheUser") // Login form should POST the credentials data to this URL"/authenticateTheUser" for processing or for matching the user id and password | No Controller Request Mapping required for this. We get this for free.
                                .permitAll() // Allow everyone to see the Login Form page. No need to be logged in.
                );
        return http.build();
    }
}
