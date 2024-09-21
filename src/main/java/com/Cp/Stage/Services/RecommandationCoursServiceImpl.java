package com.Cp.Stage.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.RecommandationCoursDTO;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Models.RecommandationCours;
import com.Cp.Stage.Repositories.ProjetRepo;
import com.Cp.Stage.Repositories.RecommandationCoursRepo;
@Service
public class RecommandationCoursServiceImpl {

    @Autowired
    private RecommandationCoursRepo recommandationCoursRepository;

    @Autowired
    private ProjetRepo projetRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    

    public ResponseEntity<?> addRecommandationCours(RecommandationCoursDTO recommandationCoursDTO) {
        try {
            RecommandationCours recommandationCours = new RecommandationCours();
            recommandationCours.setTitre(recommandationCoursDTO.getTitre());
            recommandationCours.setLien_vers_formation(recommandationCoursDTO.getLien_vers_formation());

            Optional<Projet> projetOpt = profileService.getCurrentUserProject();
            if (projetOpt.isPresent()) {
                recommandationCours.setProjet(projetOpt.get());
                recommandationCoursRepository.save(recommandationCours);
                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("Recommandation ajoutée avec succès!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Projet non trouvé."));
            }
        } catch (Exception e) {
            return handleError(e, "Erreur lors de l'ajout de la recommandation.");
        }
    }

    public ResponseEntity<?> updateRecommandationCours(Long recommandationId, RecommandationCoursDTO recommandationCoursDTO) {
        try {
            Optional<RecommandationCours> recommandationOpt = recommandationCoursRepository.findById(recommandationId);
            if (recommandationOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Recommandation non trouvée."));
            }
            RecommandationCours recommandation = recommandationOpt.get();
            recommandation.setTitre(recommandationCoursDTO.getTitre());
            recommandation.setLien_vers_formation(recommandationCoursDTO.getLien_vers_formation());
            recommandationCoursRepository.save(recommandation);
            return ResponseEntity.ok(new MessageResponse("Recommandation mise à jour avec succès!"));
        } catch (Exception e) {
            return handleError(e, "Erreur lors de la mise à jour de la recommandation.");
        }
    }

    public ResponseEntity<?> deleteRecommandationCours(Long recommandationId) {
        try {
            Optional<RecommandationCours> recommandationOpt = recommandationCoursRepository.findById(recommandationId);
            if (recommandationOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Recommandation non trouvée."));
            }
            recommandationCoursRepository.deleteById(recommandationId);
            return ResponseEntity.ok(new MessageResponse("Recommandation supprimée avec succès!"));
        } catch (Exception e) {
            return handleError(e, "Erreur lors de la suppression de la recommandation.");
        }
    }

    public ResponseEntity<?> getRecommandationById(Long recommandationId) {
        try {
            Optional<RecommandationCours> recommandationOpt = recommandationCoursRepository.findById(recommandationId);
            if (recommandationOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Recommandation non trouvée."));
            }
            return ResponseEntity.ok(recommandationOpt.get());
        } catch (Exception e) {
            return handleError(e, "Erreur lors de la récupération de la recommandation.");
        }
    }

    public ResponseEntity<?> getAllRecommandationsForProject() {
        try {
            Optional<Projet> projetOpt = profileService.getCurrentUserProject();
            if (projetOpt.isPresent()) {
                List<RecommandationCours> recommandations = recommandationCoursRepository.findByProjet(projetOpt.get());
                return ResponseEntity.ok(recommandations);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Projet non trouvé."));
            }
        } catch (Exception e) {
            return handleError(e, "Erreur lors de la récupération des recommandations.");
        }
    }

    private ResponseEntity<?> handleError(Exception e, String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(message));
    }
}
