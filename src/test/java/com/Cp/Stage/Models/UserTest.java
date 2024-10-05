package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialisation avant chaque test
        user = new User("TestU", "Tnom@example.com", "password123");
    }

    @Test
    public void testUserConstructor() {
        assertEquals("TestU", user.getUserName());
        assertEquals("Tnom@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertTrue(user.getIsAccountActivated());
    }

    @Test
    public void testSetAndGetNom() {
        user.setNom("nomTest");
        assertEquals("nomTest", user.getNom());
    }

    @Test
    public void testSetAndGetPrenom() {
        user.setPrenom("Doe");
        assertEquals("Doe", user.getPrenom());
    }

    @Test
    public void testSetAndGetEmail() {
        user.setEmail("new.email@example.com");
        assertEquals("new.email@example.com", user.getEmail());
    }

    @Test
    public void testSetAndGetDateIntegration() {
        Date now = new Date();
        user.setDateIntegration(now);
        assertEquals(now, user.getDateIntegration());
    }

    @Test
    public void testSetAndGetRoles() {
        // Test de l'ajout d'un rôle
        Role role = new Role();
        role.setName(ERole.ROLE_EMPLOYEE);

        HashSet<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
    }

    @Test
    public void testSetAndGetProfile() {
        // Test du setter et getter de Profile
        Profile profile = new Profile();
        user.setProfile(profile);
        assertEquals(profile, user.getProfile());
    }
}
