// package com.Cp.Stage.Controllers;

// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.Collections;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;

// import com.Cp.Stage.DTOs.MessageResponse;
// import com.Cp.Stage.DTOs.ProfileDTO;
// import com.Cp.Stage.Services.ProfileService;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// class ProfileControllerTestIntegration {

//     @Autowired
//     private MockMvc mockMvc;

//     @Mock
//     private ProfileService profileService;

//     @InjectMocks
//     private ProfileController profileController;

//     private ObjectMapper objectMapper;

//     @BeforeEach
//     void setUp() {
//         objectMapper = new ObjectMapper();
//     }

//     @Test
//     @WithMockUser // Mock user for testing secured endpoints
//     void testGetProfile() throws Exception {
//         ProfileDTO profileDTO = new ProfileDTO(1L, "Description", 
//             Collections.singletonList("Interest"), Collections.singletonList("Strength"));

//         // Mocking behavior without using thenReturn
//         when(profileService.getProfileCurrentUser()).thenAnswer(invocation -> profileDTO);

//         mockMvc.perform(get("/api/profile"))
//                 .andExpect(status().isOk())
//                 .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(jsonPath("$.id").value(1L))
//                 .andExpect(jsonPath("$.brefDescription").value("Description"));
//     }

//     @Test
//     @WithMockUser
//     void testUpdateProfile() throws Exception {
//         ProfileDTO profileDTO = new ProfileDTO(1L, "Updated Description", 
//             Collections.singletonList("Interest"), Collections.singletonList("Strength"));

//         // Mocking updateUserProfile
//         doAnswer(invocation -> ResponseEntity.ok(new MessageResponse("Profile updated successfully!")))
//             .when(profileService).updateUserProfile(any(ProfileDTO.class));

//         mockMvc.perform(put("/api/profile/update")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(profileDTO)))
//                 .andExpect(status().isOk())
//                 .andExpect(content().string("Profile updated successfully!"));
//     }

//     // @Test
//     // @WithMockUser
//     // void testGetEmployeesNotAssignedToAnyProject() throws Exception {
//     //     ProfileEmployeeDTO employeeDTO = new ProfileEmployeeDTO("John Doe", "johndoe", 
//     //         "johndoe@example.com", "Brief description");

//     //     // Mocking behavior
//     //     when(profileService.getEmployeesNotAssignedToAnyProject()).thenAnswer(invocation -> List.of(employeeDTO));

//     //     mockMvc.perform(get("/api/admin/profile/employees/not-assigned"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//     //             .andExpect(jsonPath("$[0].nom").value("John Doe"));
//     // }

//     // @Test
//     // @WithMockUser
//     // void testGetEmployeesAssignedToProject() throws Exception {
//     //     ProfileEmployeeDTO employeeDTO = new ProfileEmployeeDTO("Jane Doe", "janedoe", 
//     //         "janedoe@example.com", "Brief description");

//     //     // Mocking behavior
//     //     when(profileService.getEmployeesAssignedToProject()).thenAnswer(invocation -> List.of(employeeDTO));

//     //     mockMvc.perform(get("/api/admin/profile/employees/assigned"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//     //             .andExpect(jsonPath("$[0].nom").value("Jane Doe"));
//     // }

//     // @Test
//     // @WithMockUser
//     // void testGetAllEmployees() throws Exception {
//     //     ProfileEmployeeDTO employeeDTO = new ProfileEmployeeDTO("Jack Doe", "jackdoe", 
//     //         "jackdoe@example.com", "Brief description");

//     //     // Mocking behavior
//     //     when(profileService.getAllEmployees()).thenAnswer(invocation -> List.of(employeeDTO));

//     //     mockMvc.perform(get("/api/admin/profile/employees"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//     //             .andExpect(jsonPath("$[0].nom").value("Jack Doe"));
//     // }

//     // @Test
//     // @WithMockUser
//     // void testGetAllManagers() throws Exception {
//     //     ProfileEmployeeDTO managerDTO = new ProfileEmployeeDTO("Manager Doe", "managerdoe", 
//     //         "managerdoe@example.com", "Brief description");

//     //     // Mocking behavior
//     //     when(profileService.getAllManagers()).thenAnswer(invocation -> List.of(managerDTO));

//     //     mockMvc.perform(get("/api/admin/profile/managers"))
//     //             .andExpect(status().isOk())
//     //             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//     //             .andExpect(jsonPath("$[0].nom").value("Manager Doe"));
//     // }
// }
