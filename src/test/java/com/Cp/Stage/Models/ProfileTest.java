package com.Cp.Stage.Models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    private Profile profile;

    @BeforeEach
    public void setUp() {
        profile = new Profile();
        profile.setBrefDescription("Un profil développeur passionné");
        
        List<String> centresInteret = new ArrayList<>();
        centresInteret.add("Développement web");
        centresInteret.add("Intelligence artificielle");
        profile.setCentreInteret(centresInteret);

        List<String> pointsForts = new ArrayList<>();
        pointsForts.add("Résolution de problèmes");
        pointsForts.add("Travail en équipe");
        profile.setPointsForts(pointsForts);
    }

    @Test
    public void testProfileConstructor() {
        Profile newProfile = new Profile(1L, "Expert en Java", null, null, null, null, null, null, null);
        assertEquals("Expert en Java", newProfile.getBrefDescription());
    }

    @Test
    public void testSetAndGetBrefDescription() {
        profile.setBrefDescription("Un nouveau profil");
        assertEquals("Un nouveau profil", profile.getBrefDescription());
    }

    @Test
    public void testSetAndGetCentreInteret() {
        List<String> newInterets = new ArrayList<>();
        newInterets.add("Blockchain");
        profile.setCentreInteret(newInterets);

        assertEquals(1, profile.getCentreInteret().size());
        assertTrue(profile.getCentreInteret().contains("Blockchain"));
    }

    @Test
    public void testSetAndGetPointsForts() {
        List<String> newPointsForts = new ArrayList<>();
        newPointsForts.add("Leadership");
        profile.setPointsForts(newPointsForts);

        assertEquals(1, profile.getPointsForts().size());
        assertTrue(profile.getPointsForts().contains("Leadership"));
    }

    @Test
    public void testSetAndGetUser() {
        User user = new User();
        profile.setUser(user);

        assertEquals(user, profile.getUser());
    }

    @Test
    public void testSetAndGetCompetences() {
        Competence competence1 = new Competence();
        Competence competence2 = new Competence();
        List<Competence> competences = new ArrayList<>();
        competences.add(competence1);
        competences.add(competence2);
        profile.setCompetences(competences);

        assertEquals(2, profile.getCompetences().size());
        assertTrue(profile.getCompetences().contains(competence1));
        assertTrue(profile.getCompetences().contains(competence2));
    }

    @Test
    public void testSetAndGetCertifications() {
        Certification cert1 = new Certification();
        Certification cert2 = new Certification();
        List<Certification> certifications = new ArrayList<>();
        certifications.add(cert1);
        certifications.add(cert2);
        profile.setCertifications(certifications);

        assertEquals(2, profile.getCertifications().size());
        assertTrue(profile.getCertifications().contains(cert1));
        assertTrue(profile.getCertifications().contains(cert2));
    }

    @Test
    public void testSetAndGetProjet() {
        Projet projet = new Projet();
        profile.setProjet(projet);

        assertEquals(projet, profile.getProjet());
    }

    @Test
    public void testSetAndGetProjetGere() {
        Projet projetGere = new Projet();
        profile.setProjetGere(projetGere);

        assertEquals(projetGere, profile.getProjetGere());
    }
}
