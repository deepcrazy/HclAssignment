package com.deepanshu.hclassignment.configuration;

import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;
import com.deepanshu.hclassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class InitialDataLoader {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public CommandLineRunner initializeUsers() {
        return args -> {
            // Insert initial users here
            insertInitialUsers();
        };
    }

    private void insertInitialUsers() {

        for (int i = 1; i <= 5; i++) {
            User user = new User();
            Address address = new Address();
            if (i % 2 == 0) {
                user.setTitle("Mr.");
                user.setGender("Male");
            } else {
                user.setTitle("Mrs.");
                user.setGender("Female");
            }
            user.setFirstName("user" + i);
            user.setLastName("user" + i + "lastname");

            address.setStreet("12345 holling rd" + i);
            address.setCity("sydney" + i);
            address.setState("nsw" + i);
            address.setPostcode(20100 + i);

            user.setAddress(address);
            userRepository.save(user);
        }

        List<User> users = userRepository.findAll();
        System.out.println("Initialized users:");
        users.forEach(System.out::println);
    }
}
