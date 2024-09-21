package com.Cp.Stage.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.DomaineCompetenceDTO;
import com.Cp.Stage.Models.DomaineCompetence;

public interface DomaineCompetenceService  {

    public List<DomaineCompetenceDTO> getAllDomaineCompetences();
    public DomaineCompetenceDTO getDomaineCompetenceById(Long id);
    public List<DomaineCompetenceDTO> getDomaineCompetenceByProjetId(Long id);
    public DomaineCompetenceDTO createDomaineCompetence(DomaineCompetence domaineCompetence);
    public DomaineCompetenceDTO updateDomaineCompetence(Long id, DomaineCompetence domaineCompetenceDetails);
    public void deleteDomaineCompetence(Long id);
    public DomaineCompetenceDTO assignDomaineCompetenceToProjet(Long domaineCompetenceId, Long projetId);
    public DomaineCompetenceDTO unassignDomaineCompetenceFromProjet(Long domaineCompetenceId);
    public ResponseEntity<?> getAllDomaineCompetenceForProject();

}
