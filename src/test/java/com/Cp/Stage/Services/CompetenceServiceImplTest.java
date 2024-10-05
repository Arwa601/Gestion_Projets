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

import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.Models.Competence;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Repositories.CompetenceRepo;
import com.Cp.Stage.Repositories.DomaineCompetenceRepo;
import com.Cp.Stage.Repositories.ProfileRepo;

public class CompetenceServiceImplTest {

    @InjectMocks
    private CompetenceServiceImpl competenceService;

    @Mock
    private CompetenceRepo competenceRepo;

    @Mock
    private ProfileRepo profileRepo;

    @Mock
    private DomaineCompetenceRepo domaineCompetenceRepo;

    private Competence competence;
    private DomaineCompetence domaineCompetence;
    private Profile profile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        domaineCompetence = new DomaineCompetence();
        domaineCompetence.setId(1L);
        domaineCompetence.setTitre("Domaine 1");

        competence = new Competence();
        competence.setId(1L);
        competence.setTitre("Compétence 1");
        competence.setNiveau(3);
        competence.setDomaine_competence(domaineCompetence);
        competence.setProfiles(new ArrayList<>());

        profile = new Profile();
        profile.setId(1L);
        // profile.setUserName("testUser"); 
    }

    @Test
    public void testCreateCompetence() {
        CompetenceDTO competenceDTO = new CompetenceDTO();
        competenceDTO.setTitre("Compétence 1");
        competenceDTO.setNiveau(3);

        when(domaineCompetenceRepo.findById(1L)).thenReturn(Optional.of(domaineCompetence));
        when(competenceRepo.save(any(Competence.class))).thenReturn(competence);

        CompetenceDTO result = competenceService.createCompetence(competenceDTO, 1L);

        assertNotNull(result);
        assertEquals("Compétence 1", result.getTitre());
        assertEquals(3, result.getNiveau());
    }

    @Test
    public void testUpdateCompetence() {
        CompetenceDTO competenceDTO = new CompetenceDTO();
        competenceDTO.setTitre("Updated Title");
        competenceDTO.setNiveau(4);

        when(competenceRepo.findById(1L)).thenReturn(Optional.of(competence));
        when(competenceRepo.save(any(Competence.class))).thenReturn(competence);

        CompetenceDTO result = competenceService.updateCompetence(1L, competenceDTO);

        assertNotNull(result);
        assertEquals("Updated Title", result.getTitre());
        assertEquals(4, result.getNiveau());
    }

    @Test
    public void testDeleteCompetence() {
        when(competenceRepo.findById(1L)).thenReturn(Optional.of(competence));
        doNothing().when(competenceRepo).delete(any(Competence.class));

        assertDoesNotThrow(() -> competenceService.deleteCompetence(1L));
        verify(competenceRepo).delete(competence);  // Vérifie que la méthode delete a bien été appelée
    }

    @Test
    public void testGetCompetenceById() {
        when(competenceRepo.findById(1L)).thenReturn(Optional.of(competence));

        CompetenceDTO result = competenceService.getCompetenceById(1L);

        assertNotNull(result);
        assertEquals("Compétence 1", result.getTitre());
    }

    @Test
    public void testGetAllCompetences() {
        List<Competence> competences = new ArrayList<>();
        competences.add(competence);
        when(competenceRepo.findAll()).thenReturn(competences);

        List<CompetenceDTO> result = competenceService.getAllCompetences();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Compétence 1", result.get(0).getTitre());
    }

    @Test
    public void testAssignCompetenceToProfile() {
        when(competenceRepo.findById(1L)).thenReturn(Optional.of(competence));
        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));
        when(competenceRepo.save(any(Competence.class))).thenReturn(competence);

        CompetenceDTO result = competenceService.assignCompetenceToProfile(1L, 1L, 5);

        assertNotNull(result);
        assertEquals(5, result.getNiveau());
        assertTrue(competence.getProfiles().contains(profile)); 
    }
}
