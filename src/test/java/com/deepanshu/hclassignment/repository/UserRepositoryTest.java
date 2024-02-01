package com.deepanshu.hclassignment.repository;

import com.deepanshu.hclassignment.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;

@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void testGetByEmpId_ValidEmpId_ReturnsUser() {
        User mockUser = createMockUser();
        when(userRepository.findByEmpId(anyLong())).thenReturn(Optional.of(mockUser));

        User result = userServiceImpl.getUserByEmpId(1L);

        assertNotNull(result);
        assertEquals(mockUser, result);
    }

    @Test
    public void testUpdateUserByEmpId_ValidEmpId_ReturnsUser() {
        User mockUser = createMockUser();
        when(userRepository.findByEmpId(anyLong())).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User result = userServiceImpl.updateUserByEmpId(1L, mockUser);

        assertNotNull(result);
        assertEquals(mockUser, result);
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