package com.Cp.Stage.Controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Services.ProfileService;

public class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProfile_Success() {
        // Arrange
        ProfileDTO profileDTO = new ProfileDTO(1L, "Description", Arrays.asList("Interest1", "Interest2"), Arrays.asList("Strength1", "Strength2"));
        when(profileService.getProfileCurrentUser()).thenReturn(profileDTO);

        // Act
        ResponseEntity<ProfileDTO> response = profileController.getProfile();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profileDTO, response.getBody());
    }

    @Test
    public void testGetProfile_Unauthorized() {
        // Arrange
        when(profileService.getProfileCurrentUser()).thenThrow(new RuntimeException("Unauthorized"));

        // Act
        ResponseEntity<ProfileDTO> response = profileController.getProfile();

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testGetProfile_ProfileNotFound() {
        // Arrange
        when(profileService.getProfileCurrentUser()).thenThrow(new RuntimeException("Profile not found"));

        // Act
        ResponseEntity<ProfileDTO> response = profileController.getProfile();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void testUpdateProfile_Success() {
        // Arrange
        ProfileDTO profileDTO = new ProfileDTO(1L, "Updated Description", Arrays.asList("UpdatedInterest1"), Arrays.asList("UpdatedStrength1"));
        when(profileService.getProfileById(profileDTO.getId())).thenReturn(profileDTO);

        // Act
        ResponseEntity<?> response = profileController.updateProfil(profileDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(profileDTO, response.getBody());
        verify(profileService, times(1)).updateUserProfile(profileDTO);
    }

    @Test
    public void testUpdateProfile_Error() {
        // Arrange
        ProfileDTO profileDTO = new ProfileDTO(1L, "Updated Description", Arrays.asList("UpdatedInterest1"), Arrays.asList("UpdatedStrength1"));
        doThrow(new RuntimeException("Update failed")).when(profileService).updateUserProfile(profileDTO);

        // Act
        ResponseEntity<?> response = profileController.updateProfil(profileDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error updating profile: Update failed", response.getBody());
    }

    @Test
public void testGetEmployeesNotAssignedToAnyProject() {
    // Arrange
    ProfileEmployeeDTO employee = new ProfileEmployeeDTO();
    employee.setNom("Employee1");
    employee.setUsername("emp1");
    employee.setEmail("emp1@mail.com");
    employee.setBriefDescription("Description");
    
    List<ProfileEmployeeDTO> employees = Arrays.asList(employee);
    when(profileService.getEmployeesNotAssignedToAnyProject()).thenReturn(employees);

    // Act
    ResponseEntity<List<ProfileEmployeeDTO>> response = profileController.getEmployeesNotAssignedToAnyProject();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(employees, response.getBody());
}
@Test
public void testGetEmployeesAssignedToProject() {
    // Arrange
    ProfileEmployeeDTO employee = new ProfileEmployeeDTO();
    employee.setNom("Employee1");
    employee.setUsername("emp1");
    employee.setEmail("emp1@mail.com");
    employee.setBriefDescription("Description");
    
    List<ProfileEmployeeDTO> employees = Arrays.asList(employee);
    when(profileService.getEmployeesAssignedToProject()).thenReturn(employees);

    // Act
    ResponseEntity<List<ProfileEmployeeDTO>> response = profileController.getEmployeesAssignedToProject();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(employees, response.getBody());
}

@Test
public void testGetAllEmployees() {
    // Arrange
    ProfileEmployeeDTO employee = new ProfileEmployeeDTO();
    employee.setNom("Employee1");
    employee.setUsername("emp1");
    employee.setEmail("emp1@mail.com");
    employee.setBriefDescription("Description");
    
    List<ProfileEmployeeDTO> employees = Arrays.asList(employee);
    when(profileService.getAllEmployees()).thenReturn(employees);

    // Act
    ResponseEntity<List<ProfileEmployeeDTO>> response = profileController.getAllEmployees();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(employees, response.getBody());
}

@Test
public void testGetAllManagers() {
    // Arrange
    ProfileEmployeeDTO manager = new ProfileEmployeeDTO();
    manager.setNom("Manager1");
    manager.setUsername("mgr1");
    manager.setEmail("mgr1@mail.com");
    manager.setBriefDescription("Description");
    
    List<ProfileEmployeeDTO> managers = Arrays.asList(manager);
    when(profileService.getAllManagers()).thenReturn(managers);

    // Act
    ResponseEntity<List<ProfileEmployeeDTO>> response = profileController.getAllManagers();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(managers, response.getBody());
}
}
