package com.Cp.Stage.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.DomaineCompetenceDTO;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Repositories.DomaineCompetenceRepo;
import com.Cp.Stage.Repositories.ProjetRepo;


@Service
public class DomaineCompetenceServiceImpl implements DomaineCompetenceService{

    @Autowired
    private DomaineCompetenceRepo domaineCompetenceRepo;

    @Autowired
    private ProjetRepo projetRepo;

    @Autowired
    private ProfileServiceImpl profileService;



    public List<DomaineCompetenceDTO> getAllDomaineCompetences() {
        return domaineCompetenceRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DomaineCompetenceDTO getDomaineCompetenceById(Long id) {
        Optional<DomaineCompetence> domaineCompetence = domaineCompetenceRepo.findById(id);
        return domaineCompetence.map(this::convertToDTO).orElse(null);
    }

    public DomaineCompetenceDTO createDomaineCompetence(DomaineCompetence domaineCompetence) {
        return convertToDTO(domaineCompetenceRepo.save(domaineCompetence));
    }

    public DomaineCompetenceDTO updateDomaineCompetence(Long id, DomaineCompetence domaineCompetenceDetails) {
        DomaineCompetence domaineCompetence = domaineCompetenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Domaine de compétence non trouvé."));
        domaineCompetence.setTitre(domaineCompetenceDetails.getTitre());
        domaineCompetence.setDescription(domaineCompetenceDetails.getDescription());
        return convertToDTO(domaineCompetenceRepo.save(domaineCompetence));
    }

    public void deleteDomaineCompetence(Long id) {
        domaineCompetenceRepo.deleteById(id);
    }


    public DomaineCompetenceDTO assignDomaineCompetenceToProjet(Long domaineCompetenceId, Long projetId) {
        DomaineCompetence domaineCompetence = domaineCompetenceRepo.findById(domaineCompetenceId)
                .orElseThrow(() -> new RuntimeException("Domaine de compétence non trouvé."));
        Projet projet = projetRepo.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé."));
        domaineCompetence.setProjet(projet);
        return convertToDTO(domaineCompetenceRepo.save(domaineCompetence));
    }

    public DomaineCompetenceDTO unassignDomaineCompetenceFromProjet(Long domaineCompetenceId) {
        DomaineCompetence domaineCompetence = domaineCompetenceRepo.findById(domaineCompetenceId)
                .orElseThrow(() -> new RuntimeException("Domaine de compétence non trouvé."));
        domaineCompetence.setProjet(null);
        return convertToDTO(domaineCompetenceRepo.save(domaineCompetence));
    }

    public ResponseEntity<?> getAllDomaineCompetenceForProject() {
        try {
            Optional<Projet> projetOpt = profileService.getCurrentUserProject();
            if (projetOpt.isPresent()) {
                List<DomaineCompetence> recommandations = domaineCompetenceRepo.findByProjet(projetOpt.get());
                return ResponseEntity.ok(recommandations);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Projet non trouvé."));
            }
        } catch (Exception e) {
            return handleError(e, "Erreur lors de la récupération des Domaines de compétences.");
        }
    }

    public List<DomaineCompetenceDTO> getDomaineCompetenceByProjetId(Long id) {
        List<DomaineCompetence> domaineCompetences = domaineCompetenceRepo.findByProjetId(id);
        if (domaineCompetences != null && !domaineCompetences.isEmpty()) {
            return domaineCompetences.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private ResponseEntity<?> handleError(Exception e, String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(message));
    }

    private DomaineCompetenceDTO convertToDTO(DomaineCompetence domaineCompetence) {
        DomaineCompetenceDTO dto = new DomaineCompetenceDTO();
        dto.setId(domaineCompetence.getId());
        dto.setTitre(domaineCompetence.getTitre());
        dto.setDescription(domaineCompetence.getDescription());
        dto.setNomProjet(domaineCompetence.getProjet() != null ? domaineCompetence.getProjet().getNom() : null);
        return dto;
    }
}
