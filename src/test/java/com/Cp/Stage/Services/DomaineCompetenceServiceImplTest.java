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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.DomaineCompetenceDTO;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Repositories.DomaineCompetenceRepo;
import com.Cp.Stage.Repositories.ProjetRepo;

public class DomaineCompetenceServiceImplTest {

    @InjectMocks
    private DomaineCompetenceServiceImpl domaineCompetenceService;

    @Mock
    private DomaineCompetenceRepo domaineCompetenceRepo;

    @Mock
    private ProjetRepo projetRepo;

    @Mock
    private ProfileServiceImpl profileService;

    private DomaineCompetence domaineCompetence;
    private Projet projet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        projet = new Projet();
        projet.setId(1L);
        projet.setNom("Test Project");

        domaineCompetence = new DomaineCompetence();
        domaineCompetence.setId(1L);
        domaineCompetence.setTitre("Test Title");
        domaineCompetence.setDescription("Test Description");
    }

    @Test
    public void testGetAllDomaineCompetences() {
        List<DomaineCompetence> domaines = new ArrayList<>();
        domaines.add(domaineCompetence);
        when(domaineCompetenceRepo.findAll()).thenReturn(domaines);

        List<DomaineCompetenceDTO> result = domaineCompetenceService.getAllDomaineCompetences();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitre());
    }

    @Test
    public void testGetDomaineCompetenceById() {
        when(domaineCompetenceRepo.findById(1L)).thenReturn(Optional.of(domaineCompetence));

        DomaineCompetenceDTO result = domaineCompetenceService.getDomaineCompetenceById(1L);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitre());
    }

    @Test
    public void testCreateDomaineCompetence() {
        when(domaineCompetenceRepo.save(any(DomaineCompetence.class))).thenReturn(domaineCompetence);

        DomaineCompetenceDTO result = domaineCompetenceService.createDomaineCompetence(domaineCompetence);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitre());
    }

    @Test
    public void testUpdateDomaineCompetence() {
        when(domaineCompetenceRepo.findById(1L)).thenReturn(Optional.of(domaineCompetence));
        when(domaineCompetenceRepo.save(any(DomaineCompetence.class))).thenReturn(domaineCompetence);

        DomaineCompetence updatedDetails = new DomaineCompetence();
        updatedDetails.setTitre("Updated Title");
        updatedDetails.setDescription("Updated Description");

        DomaineCompetenceDTO result = domaineCompetenceService.updateDomaineCompetence(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitre());
    }

    @Test
    public void testDeleteDomaineCompetence() {
        doNothing().when(domaineCompetenceRepo).deleteById(1L);
        domaineCompetenceService.deleteDomaineCompetence(1L);
        verify(domaineCompetenceRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testAssignDomaineCompetenceToProjet() {
        when(domaineCompetenceRepo.findById(1L)).thenReturn(Optional.of(domaineCompetence));
        when(projetRepo.findById(1L)).thenReturn(Optional.of(projet));
        when(domaineCompetenceRepo.save(any(DomaineCompetence.class))).thenReturn(domaineCompetence);

        DomaineCompetenceDTO result = domaineCompetenceService.assignDomaineCompetenceToProjet(1L, 1L);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitre());
        assertEquals("Test Project", result.getNomProjet());
    }

    @Test
    public void testUnassignDomaineCompetenceFromProjet() {
        when(domaineCompetenceRepo.findById(1L)).thenReturn(Optional.of(domaineCompetence));
        when(domaineCompetenceRepo.save(any(DomaineCompetence.class))).thenReturn(domaineCompetence);

        DomaineCompetenceDTO result = domaineCompetenceService.unassignDomaineCompetenceFromProjet(1L);

        assertNotNull(result);
        assertNull(result.getNomProjet());
    }

    @Test
public void testGetAllDomaineCompetenceForProject_ExceptionHandled() {
    try {
        when(profileService.getCurrentUserProject()).thenReturn(Optional.of(projet));
        List<DomaineCompetence> domaines = new ArrayList<>();
        domaines.add(domaineCompetence);
        when(domaineCompetenceRepo.findByProjet(projet)).thenReturn(domaines);

        ResponseEntity<?> response = domaineCompetenceService.getAllDomaineCompetenceForProject();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, ((List<DomaineCompetence>) response.getBody()).size());
    } catch (Exception e) {
        fail("Exception should not have been thrown");
    }
}



    @Test
    public void testGetDomaineCompetenceByProjetId() {
        when(domaineCompetenceRepo.findByProjetId(1L)).thenReturn(List.of(domaineCompetence));

        List<DomaineCompetenceDTO> result = domaineCompetenceService.getDomaineCompetenceByProjetId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitre());
    }
}
