package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FichierSupportTest {

    private FichierSupport fichierSupport;

    @BeforeEach
    public void setUp() {
        fichierSupport = new FichierSupport();
        fichierSupport.setNom_fichier("support.pdf");
        fichierSupport.setFichier("contentOfFileInBase64");
    }

    @Test
    public void testFichierSupportConstructor() {
        Competence competence = new Competence();
        competence.setTitre("Java");

        FichierSupport newFichier = new FichierSupport(1L, "document.txt", "fileContent", competence);
        assertEquals("document.txt", newFichier.getNom_fichier());
        assertEquals("fileContent", newFichier.getFichier());
        assertEquals("Java", newFichier.getCompetence().getTitre());
    }

    @Test
    public void testSetAndGetNomFichier() {
        fichierSupport.setNom_fichier("newfile.txt");
        assertEquals("newfile.txt", fichierSupport.getNom_fichier());
    }

    @Test
    public void testSetAndGetFichier() {
        fichierSupport.setFichier("newFileContentInBase64");
        assertEquals("newFileContentInBase64", fichierSupport.getFichier());
    }

    @Test
    public void testSetAndGetCompetence() {
        Competence competence = new Competence();
        competence.setTitre("Spring Framework");

        fichierSupport.setCompetence(competence);
        assertEquals(competence, fichierSupport.getCompetence());
        assertEquals("Spring Framework", fichierSupport.getCompetence().getTitre());
    }
}
