package com.Cp.Stage.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.ProfileRepo;
import com.Cp.Stage.Repositories.ProjetRepo;
import com.Cp.Stage.Repositories.UserRepo;

public class ProfileServiceImplTest {

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ProjetRepo projetRepo;

    @Mock
    private ProfileRepo profileRepo;

    @Mock
    private Authentication authentication;

    private User user;
    private Profile profile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUserName("testUser");
        user.setId(1L);

        profile = new Profile();
        profile.setId(1L);
        profile.setUser(user);
        profile.setBrefDescription("Brief description");
        profile.setCentreInteret(new ArrayList<>());
        profile.setPointsForts(new ArrayList<>());

        // Configure SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void testGetProfileCurrentUser() {
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("testUser");
        
        User mockedUser = mock(User.class);
        when(userRepo.findFirstByUserName("testUser")).thenReturn(Optional.of(mockedUser));
        
        when(mockedUser.getProfile()).thenReturn(profile);

        ProfileDTO result = profileService.getProfileCurrentUser();

        assertNotNull(result);
        assertEquals("Brief description", result.getBrefDescription());
    }

    @Test
    public void testUpdateUserProfile() {
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("testUser");
        
        User mockedUser = mock(User.class);
        when(userRepo.findFirstByUserName("testUser")).thenReturn(Optional.of(mockedUser));
        when(mockedUser.getProfile()).thenReturn(profile);

        ProfileDTO updatedProfileDTO = new ProfileDTO();
        updatedProfileDTO.setBrefDescription("Updated description");
        updatedProfileDTO.setCentreInteret(new ArrayList<>());
        updatedProfileDTO.setPointsForts(new ArrayList<>());

        ResponseEntity<?> response = profileService.updateUserProfile(updatedProfileDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Profile updated successfully!", ((MessageResponse) response.getBody()).getMessage());
        assertEquals("Updated description", profile.getBrefDescription());
    }

    @Test
    public void testGetProfileById() {
        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));

        ProfileDTO result = profileService.getProfileById(1L);

        assertNotNull(result);
        assertEquals("Brief description", result.getBrefDescription());
    }

    @Test
    public void testGetCurrentUserAndHisProfile() {
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("testUser");
        
        // Créer un mock pour l'utilisateur
        User mockedUser = mock(User.class);
        when(userRepo.findFirstByUserName("testUser")).thenReturn(Optional.of(mockedUser));
        when(mockedUser.getProfile()).thenReturn(profile);

        ResponseEntity<?> response = profileService.getCurrentUserAndHisProfile();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Object> body = (List<Object>) response.getBody();
        assertEquals(2, body.size());
        assertEquals(mockedUser, body.get(0));
        assertEquals(profile, body.get(1));
    }

    @Test
    public void testGetEmployeesNotAssignedToAnyProject() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile);
        when(profileRepo.findAllEmployeesNotAssignedToAnyProject()).thenReturn(profiles);

        List<ProfileEmployeeDTO> result = profileService.getEmployeesNotAssignedToAnyProject();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("testUser", result.get(0).getUsername());
    }

}
