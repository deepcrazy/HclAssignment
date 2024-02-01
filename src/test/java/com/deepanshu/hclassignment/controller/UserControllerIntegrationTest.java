package com.deepanshu.hclassignment.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.deepanshu.hclassignment.model.Address;
import com.deepanshu.hclassignment.model.User;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final MappingJackson2HttpMessageConverter jsonConverter;

    public UserControllerIntegrationTest() {
        this.jsonConverter = new MappingJackson2HttpMessageConverter();
    }

    @Test
    public void getUserByEmpId_ValidEmpId_ReturnsUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/userdetails/1")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateUserByEmpId_ValidEmpIdAndUser_ReturnsUpdatedUser() throws Exception {
        User mockUser = createMockUser();
        String jsonUser = asJsonString(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/userdetails/1").content(jsonUser).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    private String asJsonString(final Object obj) throws Exception {
        return new String(jsonConverter.getObjectMapper().writeValueAsBytes(obj));
    }

    private User createMockUser() {
        Address address = new Address();
        address.setStreet("67890 Updated St");
        address.setCity("New York");
        address.setState("ny");
        address.setPostcode(10001);

        User user = new User();
        user.setTitle("mr");
        user.setFirstName("UpdatedFirstName");
        user.setLastName("UpdatedLastName");
        user.setGender("female");
        user.setAddress(address);

        return user;
    }
}
