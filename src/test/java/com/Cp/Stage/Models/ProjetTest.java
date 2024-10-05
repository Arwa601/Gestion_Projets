package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjetTest {

    private Projet projet;

    @BeforeEach
    public void setUp() {
        projet = new Projet();
        projet.setNom("Projet Alpha");
        projet.setDetails_projet("Détails du projet Alpha");
    }

    @Test
    public void testProjetConstructor() {
        Projet newProjet = new Projet(1L, "Projet Beta", "Détails du projet Beta", null, null, null, null);
        assertEquals("Projet Beta", newProjet.getNom());
        assertEquals("Détails du projet Beta", newProjet.getDetails_projet());
    }

    @Test
    public void testSetAndGetNom() {
        projet.setNom("Projet Gamma");
        assertEquals("Projet Gamma", projet.getNom());
    }

    @Test
    public void testSetAndGetDetailsProjet() {
        projet.setDetails_projet("Détails modifiés du projet Alpha");
        assertEquals("Détails modifiés du projet Alpha", projet.getDetails_projet());
    }

    @Test
    public void testSetAndGetProfiles() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);
        projet.setProfiles(profiles);

        assertEquals(2, projet.getProfiles().size());
        assertTrue(projet.getProfiles().contains(profile1));
        assertTrue(projet.getProfiles().contains(profile2));
    }

    @Test
    public void testSetAndGetManager() {
        Profile manager = new Profile();
        projet.setManager(manager);

        assertEquals(manager, projet.getManager());
    }

    @Test
    public void testSetAndGetDomainesCompetences() {
        DomaineCompetence domaine1 = new DomaineCompetence();
        DomaineCompetence domaine2 = new DomaineCompetence();
        List<DomaineCompetence> domainesCompetences = new ArrayList<>();
        domainesCompetences.add(domaine1);
        domainesCompetences.add(domaine2);
        projet.setDomaines_competences(domainesCompetences);

        assertEquals(2, projet.getDomaines_competences().size());
        assertTrue(projet.getDomaines_competences().contains(domaine1));
        assertTrue(projet.getDomaines_competences().contains(domaine2));
    }

    @Test
    public void testSetAndGetRecommandationsCours() {
        RecommandationCours recommandation1 = new RecommandationCours();
        RecommandationCours recommandation2 = new RecommandationCours();
        List<RecommandationCours> recommandationsCours = new ArrayList<>();
        recommandationsCours.add(recommandation1);
        recommandationsCours.add(recommandation2);
        projet.setRecommandations_cours(recommandationsCours);

        assertEquals(2, projet.getRecommandations_cours().size());
        assertTrue(projet.getRecommandations_cours().contains(recommandation1));
        assertTrue(projet.getRecommandations_cours().contains(recommandation2));
    }
}
