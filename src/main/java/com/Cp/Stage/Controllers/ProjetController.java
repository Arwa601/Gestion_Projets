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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProjetController {

    @Autowired
    private ProjetService projetService;

    @PostMapping("/admin/projets/add")
    public ResponseEntity<?> addProjet(@Valid @RequestBody ProjetDTO projetDTO) {
        return projetService.addProjet(projetDTO);
    }

    @PutMapping("/admin/projets/update/{id}")
    public ResponseEntity<?> updateProjet(@PathVariable("id") Long projetId, @Valid @RequestBody ProjetDTO projetDTO) {
        return projetService.updateProjet(projetId, projetDTO);
    }

    @DeleteMapping("/admin/projets/delete/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable("id") Long projetId) {
        return projetService.deleteProjet(projetId);
    }

    @GetMapping("/admin/projets/get/{id}")
    public ResponseEntity<?> getProjetById(@PathVariable("id") Long projetId) {
        return projetService.getProjetById(projetId);
    }

    @GetMapping("/admin/projets/")
    public ResponseEntity<?> getAllProjets() {
        return projetService.getAllProjets();
    }


    @PostMapping("/manager/projets/{projetId}/assignEmployee/{employeeProfileId}")
    public ResponseEntity<?> assignEmployeeToProject(@PathVariable Long projetId, @PathVariable Long employeeProfileId) {
        return projetService.assignEmployeeToProject(projetId, employeeProfileId);
    }

    @DeleteMapping("/manager/projets/{projetId}/unassignEmployee/{employeeProfileId}")
    public ResponseEntity<?> unassignEmployeeFromProject(@PathVariable Long projetId, @PathVariable Long employeeProfileId) {
        return projetService.unassignEmployeeFromProject(projetId, employeeProfileId);
    }

    @PostMapping("/admin/projets/{projetId}/assignManager/{managerProfileId}")
    public ResponseEntity<?> assignManagerToProject(@PathVariable Long projetId, @PathVariable Long managerProfileId) {
        return projetService.assignManagerToProject(projetId, managerProfileId);
    }

    @DeleteMapping("/admin/projets/{projetId}/unassignManager")
    public ResponseEntity<?> unassignManagerFromProject(@PathVariable Long projetId) {
        return projetService.unassignManagerFromProject(projetId);
    }
}
