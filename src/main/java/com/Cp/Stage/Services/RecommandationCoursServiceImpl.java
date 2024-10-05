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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@Service
public class RecommandationCoursServiceImpl {

    @Autowired
    private RecommandationCoursRepo recommandationCoursRepository;

    @Autowired
    private ProjetRepo projetRepository;

    @Autowired
    private ProfileServiceImpl profileService;

    
    @Operation(summary = "Add a new recommendation for a course")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recommandation added successfully", 
                        content = {@Content(mediaType = "application/json", 
                        schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Project not found", 
                        content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", 
                        content = @Content)
        })
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

    @Operation(summary = "Update an existing recommendation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recommandation updated successfully", 
                     content = {@Content(mediaType = "application/json", 
                     schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Recommandation not found", 
                     content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                     content = @Content)
    })
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

    @Operation(summary = "Delete a recommendation by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recommandation deleted successfully", 
                     content = {@Content(mediaType = "application/json", 
                     schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "404", description = "Recommandation not found", 
                     content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                     content = @Content)
    })
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

    @Operation(summary = "Get a recommendation by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recommandation retrieved successfully", 
                     content = {@Content(mediaType = "application/json", 
                     schema = @Schema(implementation = RecommandationCoursDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Recommandation not found", 
                     content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                     content = @Content)
    })
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

    @Operation(summary = "Get all recommendations for the current user's project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Recommandations retrieved successfully", 
                     content = {@Content(mediaType = "application/json", 
                     schema = @Schema(implementation = RecommandationCoursDTO.class))}),
        @ApiResponse(responseCode = "404", description = "Project not found", 
                     content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
                     content = @Content)
    })
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
