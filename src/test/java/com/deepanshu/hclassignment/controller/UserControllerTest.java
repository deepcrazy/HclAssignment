package com.deepanshu.hclassignment.controller;

import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;
import com.deepanshu.hclassignment.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUserByEmpId_ValidEmpId_ReturnsUser() {
        User mockUser = createMockUser();
        when(userService.getUserByEmpId(anyLong())).thenReturn(mockUser);

        ResponseEntity<User> response = userController.getUserByEmpId("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockUser, response.getBody());
    }

    @Test
    public void testUpdateUserByEmpId_ValidEmpIdAndUser_ReturnsUpdatedUser() {
        User mockUser = createMockUser();
        when(userService.updateUserByEmpId(anyLong(),any(User.class))).thenReturn(mockUser);

        ResponseEntity<User> response = userController.updateUserByEmpId("1", mockUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockUser, response.getBody());
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
