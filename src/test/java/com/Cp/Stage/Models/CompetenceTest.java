package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompetenceTest {

    private Competence competence;

    @BeforeEach
    public void setUp() {
        // Initialisation avant chaque test
        competence = new Competence();
        competence.setTitre("Java");
        competence.setNiveau(3);
    }

    @Test
    public void testCompetenceConstructor() {
        List<Profile> profiles = new ArrayList<>();
        DomaineCompetence domaine = new DomaineCompetence();
        List<FichierSupport> fichiers = new ArrayList<>();
        List<FormationSupport> formations = new ArrayList<>();
        
        Competence newCompetence = new Competence(1L, "Python", 4, profiles, domaine, fichiers, formations);
        assertEquals("Python", newCompetence.getTitre());
        assertEquals(4, newCompetence.getNiveau());
    }

    @Test
    public void testSetAndGetTitre() {
        competence.setTitre("Spring");
        assertEquals("Spring", competence.getTitre());
    }

    @Test
    public void testSetAndGetNiveau() {
        competence.setNiveau(5);
        assertEquals(5, competence.getNiveau());
    }

    @Test
    public void testSetAndGetProfiles() {
        Profile profile1 = new Profile();
        profile1.setBrefDescription("Développeur Backend");
        Profile profile2 = new Profile();
        profile2.setBrefDescription("Développeur Frontend");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        competence.setProfiles(profiles);
        assertEquals(2, competence.getProfiles().size());
        assertTrue(competence.getProfiles().contains(profile1));
        assertTrue(competence.getProfiles().contains(profile2));
    }

    @Test
    public void testSetAndGetDomaineCompetence() {
        DomaineCompetence domaine = new DomaineCompetence();
        domaine.setTitre("Informatique");
        competence.setDomaine_competence(domaine);

        assertEquals(domaine, competence.getDomaine_competence());
        assertEquals("Informatique", competence.getDomaine_competence().getTitre());
    }

    @Test
    public void testSetAndGetFichiersSupports() {
        FichierSupport fichier1 = new FichierSupport();
        fichier1.setNom_fichier("Guide Java");
        FichierSupport fichier2 = new FichierSupport();
        fichier2.setNom_fichier("Guide Spring");

        List<FichierSupport> fichiers = new ArrayList<>();
        fichiers.add(fichier1);
        fichiers.add(fichier2);

        competence.setFichiers_supports(fichiers);
        assertEquals(2, competence.getFichiers_supports().size());
        assertTrue(competence.getFichiers_supports().contains(fichier1));
        assertTrue(competence.getFichiers_supports().contains(fichier2));
    }

    @Test
    public void testSetAndGetFormationsSupports() {
        FormationSupport formation1 = new FormationSupport();
        formation1.setTitre("Formation Java");
        FormationSupport formation2 = new FormationSupport();
        formation2.setTitre("Formation Spring");

        List<FormationSupport> formations = new ArrayList<>();
        formations.add(formation1);
        formations.add(formation2);

        competence.setFormations_supports(formations);
        assertEquals(2, competence.getFormations_supports().size());
        assertTrue(competence.getFormations_supports().contains(formation1));
        assertTrue(competence.getFormations_supports().contains(formation2));
    }
}
