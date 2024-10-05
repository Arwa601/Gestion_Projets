package com.Cp.Stage.Controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProjetDTO;
import com.Cp.Stage.Services.ProjetService;

@ExtendWith(MockitoExtension.class)
public class ProjetControllerTest {

    @Mock
    private ProjetService projetService;

    @InjectMocks
    private ProjetController projetController;

    private ProjetDTO projetDTO;

    @BeforeEach
    public void setUp() {
        projetDTO = new ProjetDTO();
        projetDTO.setNom("Test Projet");
        projetDTO.setDetails_projet("Détails du projet de test");
    }

    @Test
    public void testAddProjet() {
        doReturn(ResponseEntity.status(HttpStatus.CREATED).body("Projet ajouté avec succès!"))
            .when(projetService).addProjet(any(ProjetDTO.class));

        ResponseEntity<?> response = projetController.addProjet(projetDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Projet ajouté avec succès!", response.getBody());
    }

    @Test
    public void testUpdateProjet() {
        long projetId = 1L;

        doReturn(ResponseEntity.ok("Projet mis à jour avec succès!"))
            .when(projetService).updateProjet(eq(projetId), any(ProjetDTO.class));

        ResponseEntity<?> response = projetController.updateProjet(projetId, projetDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Projet mis à jour avec succès!", response.getBody());
    }

    @Test
    public void testDeleteProjet() {
        long projetId = 1L;

        doReturn(ResponseEntity.ok("Projet supprimé avec succès!"))
            .when(projetService).deleteProjet(eq(projetId));

        ResponseEntity<?> response = projetController.deleteProjet(projetId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Projet supprimé avec succès!", response.getBody());
    }

    @Test
    public void testGetProjetById() {
        long projetId = 1L;

        doReturn(ResponseEntity.ok(projetDTO))
            .when(projetService).getProjetById(eq(projetId));

        ResponseEntity<?> response = projetController.getProjetById(projetId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projetDTO, response.getBody());
    }

    @Test
    public void testGetAllProjets() {
        doReturn(ResponseEntity.ok(Collections.singletonList(projetDTO)))
            .when(projetService).getAllProjets();

        ResponseEntity<?> response = projetController.getAllProjets();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList(projetDTO), response.getBody());
    }

    @Test
    public void testAssignEmployeeToProject() {
        long projetId = 1L;
        long employeeProfileId = 1L;

        doReturn(ResponseEntity.ok("Employee assigned to the project successfully"))
            .when(projetService).assignEmployeeToProject(eq(projetId), eq(employeeProfileId));

        ResponseEntity<?> response = projetController.assignEmployeeToProject(projetId, employeeProfileId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee assigned to the project successfully", response.getBody());
    }

    @Test
    public void testUnassignEmployeeFromProject() {
        long projetId = 1L;
        long employeeProfileId = 1L;

        doReturn(ResponseEntity.ok("Employee unassigned from the project successfully"))
            .when(projetService).unassignEmployeeFromProject(eq(projetId), eq(employeeProfileId));

        ResponseEntity<?> response = projetController.unassignEmployeeFromProject(projetId, employeeProfileId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee unassigned from the project successfully", response.getBody());
    }
}
