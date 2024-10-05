package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DomaineCompetenceTest {

    private DomaineCompetence domaineCompetence;

    @BeforeEach
    public void setUp() {
        domaineCompetence = new DomaineCompetence();
        domaineCompetence.setTitre("Développement Web");
        domaineCompetence.setDescription("Compétences dans le développement de sites web et applications.");
    }

    @Test
    public void testDomaineCompetenceConstructor() {
        DomaineCompetence newDomaine = new DomaineCompetence(1L, "Intelligence Artificielle", "Compétences IA", null, null);
        assertEquals("Intelligence Artificielle", newDomaine.getTitre());
        assertEquals("Compétences IA", newDomaine.getDescription());
    }

    @Test
    public void testSetAndGetTitre() {
        domaineCompetence.setTitre("Développement Mobile");
        assertEquals("Développement Mobile", domaineCompetence.getTitre());
    }

    @Test
    public void testSetAndGetDescription() {
        domaineCompetence.setDescription("Compétences dans la création d'applications mobiles.");
        assertEquals("Compétences dans la création d'applications mobiles.", domaineCompetence.getDescription());
    }

    @Test
    public void testSetAndGetProjet() {
        Projet projet = new Projet();
        projet.setNom("Projet Web");
        domaineCompetence.setProjet(projet);

        assertEquals(projet, domaineCompetence.getProjet());
        assertEquals("Projet Web", domaineCompetence.getProjet().getNom());
    }

    @Test
    public void testSetAndGetCompetences() {
        Competence competence1 = new Competence();
        competence1.setTitre("Java");
        Competence competence2 = new Competence();
        competence2.setTitre("HTML");

        List<Competence> competences = new ArrayList<>();
        competences.add(competence1);
        competences.add(competence2);

        domaineCompetence.setCompetences(competences);

        assertEquals(2, domaineCompetence.getCompetences().size());
        assertTrue(domaineCompetence.getCompetences().contains(competence1));
        assertTrue(domaineCompetence.getCompetences().contains(competence2));
    }
}
