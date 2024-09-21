package com.Cp.Stage.Services;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProjetDTO;

public interface ProjetService {
public ResponseEntity<?> addProjet(ProjetDTO projetDTO);
public ResponseEntity<?> updateProjet(Long projetId, ProjetDTO projetDTO);
public ResponseEntity<?> deleteProjet(Long projetId);
public ResponseEntity<?> getProjetById(Long projetId);
public ResponseEntity<?> getAllProjets();
public ResponseEntity<?> unassignManagerFromProject(Long projetId);
public ResponseEntity<?> assignManagerToProject(Long projetId, Long managerProfileId);
public ResponseEntity<?> unassignEmployeeFromProject(Long projetId, Long employeeProfileId);
public ResponseEntity<?> assignEmployeeToProject(Long projetId, Long employeeProfileId);



}
