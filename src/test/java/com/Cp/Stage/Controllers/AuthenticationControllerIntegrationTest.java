package com.Cp.Stage.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import com.Cp.Stage.DTOs.LoginRequest;
import com.Cp.Stage.DTOs.SignupRequest;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.RoleRepo;
import com.Cp.Stage.Repositories.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    // @BeforeEach
    // public void setup() {
        // userRepository.deleteAll();
        // roleRepository.deleteAll();

        // Create default roles
    //     roleRepository.save(new Role(null, ERole.ROLE_EMPLOYEE));
    //     roleRepository.save(new Role(null, ERole.ROLE_ADMIN));
    //     roleRepository.save(new Role(null, ERole.ROLE_MANAGER));
    // }

    @Test
    public void testCreateUserAccount() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUserName("testuser666");
        signupRequest.setEmail("testuser666@example.com");
        signupRequest.setPassword("password");
        signupRequest.setNom("Test");
        signupRequest.setPrenom("User");
        signupRequest.setRoles(new HashSet<>());

        mockMvc.perform(post("/api/auth/createUserAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User registered successfully!"));
    }

    @Test
    public void testSignIn() throws Exception {
        
        User user = new User("signintest", "signintest@example.com", passwordEncoder.encode("password"));
        userRepository.save(user);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName("signintest");
        loginRequest.setPassword("password");

        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("signintest"));
    }
}
