package com.deepanshu.hclassignment.configuration;

import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;
import com.deepanshu.hclassignment.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InitialDataLoader {

    private final UserService userService;

    public InitialDataLoader(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner initializeUsers() {
        return args -> {
            // Insert initial users here
            insertInitialUsers();
        };
    }

    private void insertInitialUsers() {
        List<User> initialUsers = new ArrayList<>();

        // Set user1 properties
        Address user1Address = new Address();
        user1Address.setStreet("12345 holling rd");
        user1Address.setCity("Syndey");
        user1Address.setState("nsw");
        user1Address.setPostcode(20000);

        User user1 = new User();
        user1.setFirstName("deep");
        user1.setLastName("gupta");
        user1.setGender("male");
        user1.setTitle("mr");
//        user1.setEmpId("12345");
        user1.setAddress(user1Address);

        initialUsers.add(user1);

        // Set user2 properties
        Address user2Address = new Address();
        user2Address.setStreet("11 Epsom Downs Drive");
        user2Address.setCity("North York");
        user2Address.setState("NSW");
        user2Address.setPostcode(40001);

        User user2 = new User();
        user2.setFirstName("sakshi");
        user2.setLastName("gupta");
        user2.setGender("female");
        user2.setTitle("mrs");
//        user2.setEmpId("67859");
        user2.setAddress(user2Address);

        initialUsers.add(user2);


        // Save all users during initial setup
        for (User user : initialUsers) {
            userService.saveUser(user);
        }
    }
}
