package com.deepanshu.hclassignment.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;

import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmpId_ValidEmpId_ReturnsUser() {
        User mockUser = createMockUser();
        userRepository.save(mockUser);

        Optional<User> result = userRepository.findByEmpId(1L);

        assertTrue(result.isPresent());
        assertEquals(mockUser, result.get());
    }

    private User createMockUser() {
        Address address = new Address();
        address.setId(1L);
        address.setStreet("67890 Updated St");
        address.setCity("New York");
        address.setState("ny");
        address.setPostcode(10001);

        User user = new User();
        user.setEmpId(1L);
        user.setTitle("mr");
        user.setFirstName("UpdatedFirstName");
        user.setLastName("UpdatedLastName");
        user.setGender("female");
        user.setAddress(address);
        return user;
    }
}
