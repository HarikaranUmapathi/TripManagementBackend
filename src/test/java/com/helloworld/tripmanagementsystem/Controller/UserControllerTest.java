package com.helloworld.tripmanagementsystem.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.tripmanagementsystem.DTO.UserDTO;
import com.helloworld.tripmanagementsystem.Entity.UserEntity;
import com.helloworld.tripmanagementsystem.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class,
        excludeAutoConfiguration = {
                HibernateJpaAutoConfiguration.class
        })
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSignup() throws Exception {

        UserDTO dto = new UserDTO();
        dto.setUserName("Hari");
        dto.setPassword("Hari@123");
        dto.setRole("USER");

        when(service.registerUser(any(UserDTO.class)))
                .thenReturn("User registered successfully!");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }

    @Test
    void testLoginSuccess() throws Exception {

        UserEntity login = new UserEntity();
        login.setUserName("Hari");
        login.setPassword("Hari@123");

        when(service.authenticateUser("Hari", "Hari@123"))
                .thenReturn("Success");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(content().string("Success"));
    }

    @Test
    void testLoginFailure() throws Exception {

        UserEntity login = new UserEntity();
        login.setUserName("Hari");
        login.setPassword("wrong");

        when(service.authenticateUser("Hari", "wrong"))
                .thenReturn(null);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }
}
