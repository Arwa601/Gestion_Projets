package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecommandationCoursTest {

    private RecommandationCours recommandationCours;

    @BeforeEach
    public void setUp() {
        recommandationCours = new RecommandationCours();
        recommandationCours.setTitre("Cours Java");
        recommandationCours.setLien_vers_formation("https://formation-java.com");
    }

    @Test
    public void testRecommandationCoursConstructor() {
        RecommandationCours newRecommandation = new RecommandationCours(1L, "Cours Spring", "https://formation-spring.com", null);
        assertEquals("Cours Spring", newRecommandation.getTitre());
        assertEquals("https://formation-spring.com", newRecommandation.getLien_vers_formation());
    }

    @Test
    public void testSetAndGetTitre() {
        recommandationCours.setTitre("Cours Python");
        assertEquals("Cours Python", recommandationCours.getTitre());
    }

    @Test
    public void testSetAndGetLienVersFormation() {
        recommandationCours.setLien_vers_formation("https://formation-python.com");
        assertEquals("https://formation-python.com", recommandationCours.getLien_vers_formation());
    }

    @Test
    public void testSetAndGetProjet() {
        Projet projet = new Projet();
        projet.setNom("Projet Big Data");
        recommandationCours.setProjet(projet);

        assertEquals(projet, recommandationCours.getProjet());
        assertEquals("Projet Big Data", recommandationCours.getProjet().getNom());
    }
}
