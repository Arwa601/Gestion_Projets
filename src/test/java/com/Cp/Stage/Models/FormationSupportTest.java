package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormationSupportTest {

    private FormationSupport formationSupport;

    @BeforeEach
    public void setUp() {
        formationSupport = new FormationSupport();
        formationSupport.setTitre("Formation Java");
        formationSupport.setLien_vers_formation("http://example.com/formation-java");
    }

    @Test
    public void testFormationSupportConstructor() {
        Competence competence = new Competence();
        competence.setTitre("Java");

        FormationSupport newFormation = new FormationSupport(1L, "Formation Spring", "http://example.com/formation-spring", competence);
        assertEquals("Formation Spring", newFormation.getTitre());
        assertEquals("http://example.com/formation-spring", newFormation.getLien_vers_formation());
        assertEquals("Java", newFormation.getCompetence().getTitre());
    }

    @Test
    public void testSetAndGetTitre() {
        formationSupport.setTitre("Nouvelle Formation");
        assertEquals("Nouvelle Formation", formationSupport.getTitre());
    }

    @Test
    public void testSetAndGetLienVersFormation() {
        formationSupport.setLien_vers_formation("http://example.com/nouvelle-formation");
        assertEquals("http://example.com/nouvelle-formation", formationSupport.getLien_vers_formation());
    }

    @Test
    public void testSetAndGetCompetence() {
        Competence competence = new Competence();
        competence.setTitre("Spring Framework");

        formationSupport.setCompetence(competence);
        assertEquals(competence, formationSupport.getCompetence());
        assertEquals("Spring Framework", formationSupport.getCompetence().getTitre());
    }
}
