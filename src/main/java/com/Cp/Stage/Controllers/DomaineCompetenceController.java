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

@RestController
@RequestMapping("/api")
public class DomaineCompetenceController {

    @Autowired
    private DomaineCompetenceService domaineCompetenceService;

    @GetMapping("/domaineCompetence/")
    public List<DomaineCompetenceDTO> getAllDomaineCompetences() {
        return domaineCompetenceService.getAllDomaineCompetences();
    }



    @GetMapping("/domaineCompetence/{id}")
    public ResponseEntity<DomaineCompetenceDTO> getDomaineCompetenceById(@PathVariable Long id) {
        DomaineCompetenceDTO domaineCompetence = domaineCompetenceService.getDomaineCompetenceById(id);
        if (domaineCompetence != null) {
            return ResponseEntity.ok(domaineCompetence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/domaineCompetence/currentUserProject")
    public ResponseEntity<?> getAllDomaineCompetenceForProject() {
        return domaineCompetenceService.getAllDomaineCompetenceForProject();
    }

    @GetMapping("/domaineCompetence/project/{id}")
    public ResponseEntity<List<DomaineCompetenceDTO>> getDomaineCompetencesByProjetId(@PathVariable Long id) {
        List<DomaineCompetenceDTO> domaineCompetences = domaineCompetenceService.getDomaineCompetenceByProjetId(id);
        
        if (domaineCompetences != null && !domaineCompetences.isEmpty()) {
            return ResponseEntity.ok(domaineCompetences);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/manager/domaineCompetence/add")
    public DomaineCompetenceDTO createDomaineCompetence(@RequestBody DomaineCompetence domaineCompetence) {
        return domaineCompetenceService.createDomaineCompetence(domaineCompetence);
    }

    @PutMapping("/manager/domaineCompetence/{id}")
    public DomaineCompetenceDTO updateDomaineCompetence(@PathVariable Long id, @RequestBody DomaineCompetence domaineCompetenceDetails) {
        return domaineCompetenceService.updateDomaineCompetence(id, domaineCompetenceDetails);
    }

    @DeleteMapping("/manager/domaineCompetence/{id}")
    public ResponseEntity<Void> deleteDomaineCompetence(@PathVariable Long id) {
        domaineCompetenceService.deleteDomaineCompetence(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/manager/domaineCompetence/assign/{domaineCompetenceId}/projet/{projetId}")
    public DomaineCompetenceDTO assignDomaineCompetenceToProjet(@PathVariable Long domaineCompetenceId, @PathVariable Long projetId) {
        return domaineCompetenceService.assignDomaineCompetenceToProjet(domaineCompetenceId, projetId);
    }

    @PutMapping("/manager/domaineCompetence/unassign/{domaineCompetenceId}")
    public DomaineCompetenceDTO unassignDomaineCompetenceFromProjet(@PathVariable Long domaineCompetenceId) {
        return domaineCompetenceService.unassignDomaineCompetenceFromProjet(domaineCompetenceId);
    }
}
