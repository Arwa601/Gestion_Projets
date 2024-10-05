package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
        role.setName(ERole.ROLE_ADMIN);
    }

    @Test
    public void testRoleConstructor() {
        Role newRole = new Role(1, ERole.ROLE_EMPLOYEE);
        assertEquals(1, newRole.getId());
        assertEquals(ERole.ROLE_EMPLOYEE, newRole.getName());
    }

    @Test
    public void testSetAndGetId() {
        role.setId(2);
        assertEquals(2, role.getId());
    }

    @Test
    public void testSetAndGetName() {
        role.setName(ERole.ROLE_ADMIN); 
        assertEquals(ERole.ROLE_ADMIN, role.getName());
    }
}
