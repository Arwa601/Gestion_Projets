package com.Cp.Stage.Controllers;

import java.util.List;

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

import com.Cp.Stage.DTOs.DomaineCompetenceDTO;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Services.DomaineCompetenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class DomaineCompetenceController {

    @Autowired
    private DomaineCompetenceService domaineCompetenceService;

    @Operation(summary = "Get all domain competences", description = "Retrieve a list of all domain competences.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/domaineCompetence/")
    public List<DomaineCompetenceDTO> getAllDomaineCompetences() {
        return domaineCompetenceService.getAllDomaineCompetences();
    }


    @Operation(summary = "Get domain competence by ID", description = "Retrieve a specific domain competence by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competence found and returned."),
        @ApiResponse(responseCode = "404", description = "Domain competence not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/domaineCompetence/{id}")
    public ResponseEntity<DomaineCompetenceDTO> getDomaineCompetenceById(@PathVariable Long id) {
        DomaineCompetenceDTO domaineCompetence = domaineCompetenceService.getDomaineCompetenceById(id);
        if (domaineCompetence != null) {
            return ResponseEntity.ok(domaineCompetence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get domain competences for current user's project", description = "Retrieve all domain competences associated with the current user's project.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competences found and returned."),
        @ApiResponse(responseCode = "404", description = "Project not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/domaineCompetence/currentUserProject")
    public ResponseEntity<?> getAllDomaineCompetenceForProject() {
        return domaineCompetenceService.getAllDomaineCompetenceForProject();
    }

    @Operation(summary = "Get domain competences by project ID", description = "Retrieve all domain competences associated with a specific project by the project ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competences found and returned."),
        @ApiResponse(responseCode = "204", description = "No content found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/domaineCompetence/project/{id}")
    public ResponseEntity<List<DomaineCompetenceDTO>> getDomaineCompetencesByProjetId(@PathVariable Long id) {
        List<DomaineCompetenceDTO> domaineCompetences = domaineCompetenceService.getDomaineCompetenceByProjetId(id);
        
        if (domaineCompetences != null && !domaineCompetences.isEmpty()) {
            return ResponseEntity.ok(domaineCompetences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Create a new domain competence", description = "Add a new domain competence.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competence created successfully."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/manager/domaineCompetence/add")
    public DomaineCompetenceDTO createDomaineCompetence(@RequestBody DomaineCompetence domaineCompetence) {
        return domaineCompetenceService.createDomaineCompetence(domaineCompetence);
    }


    @Operation(summary = "Update a domain competence", description = "Update an existing domain competence by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competence updated successfully."),
        @ApiResponse(responseCode = "404", description = "Domain competence not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PutMapping("/manager/domaineCompetence/{id}")
    public DomaineCompetenceDTO updateDomaineCompetence(@PathVariable Long id, @RequestBody DomaineCompetence domaineCompetenceDetails) {
        return domaineCompetenceService.updateDomaineCompetence(id, domaineCompetenceDetails);
    }

    @Operation(summary = "Delete a domain competence", description = "Delete an existing domain competence by its ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Domain competence deleted successfully."),
        @ApiResponse(responseCode = "404", description = "Domain competence not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @DeleteMapping("/manager/domaineCompetence/{id}")
    public ResponseEntity<Void> deleteDomaineCompetence(@PathVariable Long id) {
        domaineCompetenceService.deleteDomaineCompetence(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Assign a domain competence to a project", description = "Assign an existing domain competence to a specific project by project ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competence assigned successfully."),
        @ApiResponse(responseCode = "404", description = "Domain competence or project not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PutMapping("/manager/domaineCompetence/assign/{domaineCompetenceId}/projet/{projetId}")
    public DomaineCompetenceDTO assignDomaineCompetenceToProjet(@PathVariable Long domaineCompetenceId, @PathVariable Long projetId) {
        return domaineCompetenceService.assignDomaineCompetenceToProjet(domaineCompetenceId, projetId);
    }
    
    @Operation(summary = "Unassign a domain competence from a project", description = "Unassign an existing domain competence from its associated project.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Domain competence unassigned successfully."),
        @ApiResponse(responseCode = "404", description = "Domain competence not found."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PutMapping("/manager/domaineCompetence/unassign/{domaineCompetenceId}")
    public DomaineCompetenceDTO unassignDomaineCompetenceFromProjet(@PathVariable Long domaineCompetenceId) {
        return domaineCompetenceService.unassignDomaineCompetenceFromProjet(domaineCompetenceId);
    }
}
