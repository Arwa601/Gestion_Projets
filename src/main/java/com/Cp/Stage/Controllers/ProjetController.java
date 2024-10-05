package com.Cp.Stage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.ProjetDTO;
import com.Cp.Stage.Services.ProjetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "ProjetController", description = "Gestion des projets (CRUD et assignations)")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @Operation(summary = "Ajouter un projet", description = "Permet d'ajouter un nouveau projet.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Projet ajouté avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Erreur lors de l'ajout du projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/admin/projets/add")
    public ResponseEntity<?> addProjet(@Valid @RequestBody ProjetDTO projetDTO) {
        return projetService.addProjet(projetDTO);
    }

    @Operation(summary = "Mettre à jour un projet", description = "Permet de mettre à jour les informations d'un projet existant.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Projet mis à jour avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet non trouvé", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la mise à jour du projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/admin/projets/update/{id}")
    public ResponseEntity<?> updateProjet(@PathVariable("id") Long projetId, @Valid @RequestBody ProjetDTO projetDTO) {
        return projetService.updateProjet(projetId, projetDTO);
    }

    @Operation(summary = "Supprimer un projet", description = "Permet de supprimer un projet en fournissant son ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Projet supprimé avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet non trouvé", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la suppression du projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/admin/projets/delete/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable("id") Long projetId) {
        return projetService.deleteProjet(projetId);
    }

    @Operation(summary = "Récupérer un projet par ID", description = "Permet de récupérer les détails d'un projet en fournissant son ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Projet récupéré avec succès", content = @Content(schema = @Schema(implementation = ProjetDTO.class))),
        @ApiResponse(responseCode = "404", description = "Projet non trouvé", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la récupération du projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/admin/projets/get/{id}")
    public ResponseEntity<?> getProjetById(@PathVariable("id") Long projetId) {
        return projetService.getProjetById(projetId);
    }

    @Operation(summary = "Lister tous les projets", description = "Permet de récupérer tous les projets.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Projets récupérés avec succès", content = @Content(schema = @Schema(implementation = ProjetDTO.class))),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la récupération des projets", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/admin/projets/")
    public ResponseEntity<?> getAllProjets() {
        return projetService.getAllProjets();
    }

    @Operation(summary = "Assigner un employé à un projet", description = "Permet d'assigner un employé à un projet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employé assigné au projet avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet ou Employé non trouvé", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Employé déjà assigné au projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/manager/projets/{projetId}/assignEmployee/{employeeProfileId}")
    public ResponseEntity<?> assignEmployeeToProject(@PathVariable Long projetId, @PathVariable Long employeeProfileId) {
        return projetService.assignEmployeeToProject(projetId, employeeProfileId);
    }
    @Operation(summary = "Désassigner un employé d'un projet", description = "Permet de désassigner un employé d'un projet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employé désassigné du projet avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet ou Employé non trouvé", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "400", description = "Employé non assigné au projet", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/manager/projets/{projetId}/unassignEmployee/{employeeProfileId}")
    public ResponseEntity<?> unassignEmployeeFromProject(@PathVariable Long projetId, @PathVariable Long employeeProfileId) {
        return projetService.unassignEmployeeFromProject(projetId, employeeProfileId);
    }

    @Operation(summary = "Assigner un manager à un projet", description = "Permet d'assigner un manager à un projet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Manager assigné au projet avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet ou Manager non trouvé", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/admin/projets/{projetId}/assignManager/{managerProfileId}")
    public ResponseEntity<?> assignManagerToProject(@PathVariable Long projetId, @PathVariable Long managerProfileId) {
        return projetService.assignManagerToProject(projetId, managerProfileId);
    }

    @Operation(summary = "Désassigner un manager d'un projet", description = "Permet de désassigner un manager d'un projet.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Manager désassigné du projet avec succès", content = @Content(schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "404", description = "Projet non trouvé", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/admin/projets/{projetId}/unassignManager")
    public ResponseEntity<?> unassignManagerFromProject(@PathVariable Long projetId) {
        return projetService.unassignManagerFromProject(projetId);
    }
}
