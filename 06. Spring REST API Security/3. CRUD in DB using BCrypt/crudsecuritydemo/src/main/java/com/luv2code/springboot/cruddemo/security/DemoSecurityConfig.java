package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
// Spring @Configuration annotation is part of the spring core framework. Spring Configuration annotation indicates that the class has @Bean definition methods. So Spring container can process the class and generate Spring Beans to be used in the application. Spring @Configuration annotation allows us to use annotations for dependency injection.
public class DemoSecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) // The DataSource works as a factory for providing database connections. It is an alternative to the DriverManager facility. A datasource uses a URL along with username/password credentials to establish the database connection.
    {
        return new JdbcUserDetailsManager(dataSource); // Tell Spring to use JDBC authentication with our Data Source

        // For Bcrypt: No need to change Java source code
        /*
        Spring Security knows that it's using a predefined table schema/table name. So Spring Security will look in a table called Users [PREDEFINED TABLE NAME by SPRING]
        and another table called Authorities [PREDEFINED TABLE NAME by SPRING]. It knows the exact column names that it'll use. It's kind of just built-in out of the box.
        */
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE") // The ** syntax: match on all sub-paths
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")); // The ** syntax: match on all sub-paths
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF) | In general, CSRF is not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build(); // Based on the method signature, we need to return an instance of the SecurityFilterChain. The http.build() method will provide this instance object. The http.build() makes use of the Builder design pattern for creating an instance.

    }

    /*
    ---------------------------- InMemory HardCoded Users --------------------------------
    @Bean
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
    */

}
