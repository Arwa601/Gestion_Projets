package com.Cp.Stage.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProjetDTO;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Repositories.ProfileRepo;
import com.Cp.Stage.Repositories.ProjetRepo;

public class ProjetServiceImplTest {

    @InjectMocks
    private ProjetServiceImpl projetService;

    @Mock
    private ProjetRepo projetRepository;

    @Mock
    private ProfileRepo profileRepo;

    private Projet projet;
    private Profile profile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projet = new Projet();
        projet.setId(1L);
        projet.setNom("Test Project");
        projet.setDetails_projet("Details of the Test Project");
        projet.setProfiles(new ArrayList<>());

        profile = new Profile();
        profile.setId(1L);
        profile.setBrefDescription("Test Profile");
    }

    @Test
    public void testAddProjet() {
        ProjetDTO projetDTO = new ProjetDTO();
        projetDTO.setNom("Test Project");
        projetDTO.setDetails_projet("Details of the Test Project");

        when(projetRepository.save(any(Projet.class))).thenReturn(projet);
        
        ResponseEntity<?> response = projetService.addProjet(projetDTO);
        
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Projet ajouté avec succès!", response.getBody());
    }

    @Test
    public void testUpdateProjet() {
        ProjetDTO projetDTO = new ProjetDTO();
        projetDTO.setNom("Updated Project");
        projetDTO.setDetails_projet("Updated details");

        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        when(projetRepository.save(any(Projet.class))).thenReturn(projet);

        ResponseEntity<?> response = projetService.updateProjet(1L, projetDTO);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Projet mis à jour avec succès!", response.getBody());
    }

    @Test
    public void testDeleteProjet() {
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        
        ResponseEntity<?> response = projetService.deleteProjet(1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Projet supprimé avec succès!", response.getBody());
    }

    @Test
    public void testGetProjetById() {
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        
        ResponseEntity<?> response = projetService.getProjetById(1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(projet, response.getBody());
    }

    @Test
    public void testGetAllProjets() {
        List<Projet> projets = new ArrayList<>();
        projets.add(projet);
        
        when(projetRepository.findAll()).thenReturn(projets);
        
        ResponseEntity<?> response = projetService.getAllProjets();
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(projets, response.getBody());
    }

    @Test
    public void testAssignEmployeeToProject() {
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));

        ResponseEntity<?> response = projetService.assignEmployeeToProject(1L, 1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Employee assigned to the project successfully", response.getBody());
    }

    @Test
    public void testUnassignEmployeeFromProject() {
        projet.getProfiles().add(profile);
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));

        ResponseEntity<?> response = projetService.unassignEmployeeFromProject(1L, 1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Employee unassigned from the project successfully", response.getBody());
    }

    @Test
    public void testAssignManagerToProject() {
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));
        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));

        ResponseEntity<?> response = projetService.assignManagerToProject(1L, 1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Manager assigned to the project successfully", response.getBody());
    }

    @Test
    public void testUnassignManagerFromProject() {
        projet.setManager(profile);
        when(projetRepository.findById(1L)).thenReturn(Optional.of(projet));

        ResponseEntity<?> response = projetService.unassignManagerFromProject(1L);
        
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Manager unassigned from the project successfully", response.getBody());
    }
}
