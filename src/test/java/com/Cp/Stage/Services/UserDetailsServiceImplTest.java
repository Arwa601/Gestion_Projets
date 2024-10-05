package com.Cp.Stage.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Cp.Stage.Models.ERole;
import com.Cp.Stage.Models.Role;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.UserRepo;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUserName("testUser");
        user.setPassword("password");
        user.setIsAccountActivated(true);
        user.setRoles(Collections.singleton(new Role(1, ERole.ROLE_EMPLOYEE)));
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        when(userRepository.findFirstByUserName("testUser")).thenReturn(Optional.of(user));
        
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        assertEquals("testUser", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findFirstByUserName("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("nonExistentUser");
        });
    }

    @Test
    public void testLoadUserByUsername_AccountNotActivated() {
        user.setIsAccountActivated(false);
        when(userRepository.findFirstByUserName("testUser")).thenReturn(Optional.of(user));

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("testUser");
        });
    }

    @Test
    public void testActivateUser() {
        when(userRepository.findFirstByUserName("testUser")).thenReturn(Optional.of(user));
        userDetailsService.activateUser("testUser");

        assertTrue(user.getIsAccountActivated());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeactivateUser() {
        when(userRepository.findFirstByUserName("testUser")).thenReturn(Optional.of(user));
        userDetailsService.deactivateUser("testUser");

        assertFalse(user.getIsAccountActivated());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testActivateUser_UserNotFound() {
        when(userRepository.findFirstByUserName("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userDetailsService.activateUser("nonExistentUser");
        });
    }

    @Test
    public void testDeactivateUser_UserNotFound() {
        when(userRepository.findFirstByUserName("nonExistentUser")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userDetailsService.deactivateUser("nonExistentUser");
        });
    }
}
