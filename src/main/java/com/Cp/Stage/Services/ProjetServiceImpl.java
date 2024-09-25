package com.Cp.Stage.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.ProjetDTO;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Repositories.ProfileRepo;
import com.Cp.Stage.Repositories.ProjetRepo;

@Service
public class ProjetServiceImpl implements ProjetService{

    @Autowired
    private ProfileRepo profileRepo; 
    @Autowired
    private ProjetRepo projetRepository;

    @Override
    public ResponseEntity<?> addProjet(ProjetDTO projetDTO) {
        try {
            Projet projet = new Projet();
            projet.setNom(projetDTO.getNom());
            projet.setDetails_projet(projetDTO.getDetails_projet());
            projetRepository.save(projet);
            return ResponseEntity.status(HttpStatus.CREATED).body("Project added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error.");
        }
    }
    @Override
    public ResponseEntity<?> updateProjet(Long projetId, ProjetDTO projetDTO) {
        try {
            Optional<Projet> projetOpt = projetRepository.findById(projetId);
            if (projetOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : Projet non trouvé");
            }
            Projet projet = projetOpt.get();
            projet.setNom(projetDTO.getNom());
            projet.setDetails_projet(projetDTO.getDetails_projet());
            projetRepository.save(projet);
            return ResponseEntity.ok("Projet mis à jour avec succès!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour du projet.");
        }
    }

    @Override
    public ResponseEntity<?> deleteProjet(Long projetId) {
        try {
            Optional<Projet> projetOpt = projetRepository.findById(projetId);
            if (projetOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : Projet non trouvé");
            }
            projetRepository.deleteById(projetId);
            return ResponseEntity.ok("Projet supprimé avec succès!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du projet.");
        }
    }

    @Override
    public ResponseEntity<?> getProjetById(Long projetId) {
        try {
            Optional<Projet> projetOpt = projetRepository.findById(projetId);
            if (projetOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : Projet non trouvé");
            }
            return ResponseEntity.ok(projetOpt.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération du projet.");
        }
    }
    @Override
    public ResponseEntity<?> getAllProjets() {
        try {
            return ResponseEntity.ok(projetRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des projets.");
        }
    }
    @Override
    public ResponseEntity<?> assignEmployeeToProject(Long projetId, Long employeeProfileId) {
        Optional<Projet> projetOpt = projetRepository.findById(projetId);
        Optional<Profile> profileOpt = profileRepo.findById(employeeProfileId);

        if (projetOpt.isPresent() && profileOpt.isPresent()) {
            Projet projet = projetOpt.get();
            Profile profile = profileOpt.get();

            // Check if the profile is already assigned to the project
            if (projet.getProfiles().contains(profile)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee already assigned to this project");
            }

            projet.getProfiles().add(profile);
            profile.setProjet(projet); 
            projetRepository.save(projet);
            return ResponseEntity.ok("Employee assigned to the project successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project or Employee not found");
    }
    @Override
    public ResponseEntity<?> unassignEmployeeFromProject(Long projetId, Long employeeProfileId) {
        Optional<Projet> projetOpt = projetRepository.findById(projetId);
        Optional<Profile> profileOpt = profileRepo.findById(employeeProfileId);

        if (projetOpt.isPresent() && profileOpt.isPresent()) {
            Projet projet = projetOpt.get();
            Profile profile = profileOpt.get();

            if (!projet.getProfiles().contains(profile)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee is not assigned to this project");
            }

            projet.getProfiles().remove(profile);
            profile.setProjet(null);
            projetRepository.save(projet);
            return ResponseEntity.ok("Employee unassigned from the project successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project or Employee not found");
    }
    @Override
    public ResponseEntity<?> assignManagerToProject(Long projetId, Long managerProfileId) {
        Optional<Projet> projetOpt = projetRepository.findById(projetId);
        Optional<Profile> profileOpt = profileRepo.findById(managerProfileId);

        if (projetOpt.isPresent() && profileOpt.isPresent()) {
            Projet projet = projetOpt.get();
            Profile profile = profileOpt.get();

            projet.setManager(profile);
            projetRepository.save(projet);
            return ResponseEntity.ok("Manager assigned to the project successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project or Manager not found");
    }
    @Override
    public ResponseEntity<?> unassignManagerFromProject(Long projetId) {
        Optional<Projet> projetOpt = projetRepository.findById(projetId);

        if (projetOpt.isPresent()) {
            Projet projet = projetOpt.get();

            projet.setManager(null);
            projetRepository.save(projet);
            return ResponseEntity.ok("Manager unassigned from the project successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
    }
}

