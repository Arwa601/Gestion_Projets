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

import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.RecommandationCoursDTO;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Models.RecommandationCours;
import com.Cp.Stage.Repositories.ProjetRepo;
import com.Cp.Stage.Repositories.RecommandationCoursRepo;

public class RecommandationCoursServiceImplTest {

    @InjectMocks
    private RecommandationCoursServiceImpl recommandationCoursService;

    @Mock
    private RecommandationCoursRepo recommandationCoursRepository;

    @Mock
    private ProjetRepo projetRepository;

    @Mock
    private ProfileServiceImpl profileService;

    private RecommandationCoursDTO recommandationCoursDTO;
    private RecommandationCours recommandationCours;
    private Projet projet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        recommandationCoursDTO = new RecommandationCoursDTO();
        recommandationCoursDTO.setTitre("Formation A");
        recommandationCoursDTO.setLien_vers_formation("http://example.com");

        projet = new Projet();
        projet.setId(1L);
        projet.setNom("Projet A");

        recommandationCours = new RecommandationCours();
        recommandationCours.setId(1L);
        recommandationCours.setTitre("Formation A");
        recommandationCours.setLien_vers_formation("http://example.com");
        recommandationCours.setProjet(projet);
    }

    @Test
    public void testAddRecommandationCours_Success() {
        try {
            when(profileService.getCurrentUserProject()).thenReturn(Optional.of(projet));
            when(recommandationCoursRepository.save(any(RecommandationCours.class))).thenReturn(recommandationCours);

            ResponseEntity<?> response = recommandationCoursService.addRecommandationCours(recommandationCoursDTO);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals("Recommandation ajoutée avec succès!", ((MessageResponse) response.getBody()).getMessage());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    public void testGetAllRecommandationsForProject_Success() {
        try {
            List<RecommandationCours> recommandations = new ArrayList<>();
            recommandations.add(recommandationCours);
            
            when(profileService.getCurrentUserProject()).thenReturn(Optional.of(projet));
            when(recommandationCoursRepository.findByProjet(projet)).thenReturn(recommandations);

            ResponseEntity<?> response = recommandationCoursService.getAllRecommandationsForProject();

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(recommandations, response.getBody());
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }
}


