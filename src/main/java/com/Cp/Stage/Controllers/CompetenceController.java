package com.Cp.Stage.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.Services.CompetenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @Operation(summary = "Créer une compétence", description = "Créer une nouvelle compétence pour un domaine de compétence")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Compétence créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Domaine de compétence non trouvé")
    })
    @PostMapping("/manager/create/{domaineCompetenceId}")
    public ResponseEntity<CompetenceDTO> createCompetence(@PathVariable Long domaineCompetenceId, @RequestBody CompetenceDTO competenceDTO) {
        CompetenceDTO newCompetence = competenceService.createCompetence(competenceDTO, domaineCompetenceId);
        return new ResponseEntity<>(newCompetence, HttpStatus.CREATED);
    }

    @Operation(summary = "Mettre à jour une compétence", description = "Mettre à jour les détails d'une compétence existante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Compétence mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @PutMapping("/manager/update/{id}")
    public ResponseEntity<CompetenceDTO> updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO competenceDTO) {
        CompetenceDTO updatedCompetence = competenceService.updateCompetence(id, competenceDTO);
        return new ResponseEntity<>(updatedCompetence, HttpStatus.OK);
    }

    @Operation(summary = "Supprimer une compétence", description = "Supprimer une compétence par son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Compétence supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @DeleteMapping("/manager/delete/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Lister toutes les compétences", description = "Récupérer la liste de toutes les compétences disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des compétences récupérée avec succès")
    })
    @GetMapping("/all")
    public ResponseEntity<List<CompetenceDTO>> getAllCompetences() {
        List<CompetenceDTO> competences = competenceService.getAllCompetences();
        return ResponseEntity.ok(competences);
    }
    @Operation(summary = "Récupérer une compétence par ID", description = "Récupérer les détails d'une compétence via son ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Détails de la compétence récupérés avec succès"),
        @ApiResponse(responseCode = "404", description = "Compétence non trouvée")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDTO> getCompetenceById(@PathVariable Long id) {
        CompetenceDTO competence = competenceService.getCompetenceById(id);
        return ResponseEntity.ok(competence);
    }

    @Operation(summary = "Assigner une compétence à un profil", description = "Assigner une compétence à un employé avec un certain niveau")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Compétence assignée avec succès"),
        @ApiResponse(responseCode = "404", description = "Compétence ou profil non trouvé")
    })
    @PutMapping("/employee/assign/{competenceId}/{profileId}")
    public ResponseEntity<CompetenceDTO> assignCompetenceToProfile(@PathVariable Long competenceId, 
                                                                @PathVariable Long profileId, 
                                                                @RequestParam Integer niveau) {
        CompetenceDTO updatedCompetence = competenceService.assignCompetenceToProfile(competenceId, profileId, niveau);
        return ResponseEntity.ok(updatedCompetence);
    }

}
