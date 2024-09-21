package com.Cp.Stage.Services;

import java.util.List;

import com.Cp.Stage.DTOs.CompetenceDTO;

public interface CompetenceService {
    public CompetenceDTO createCompetence(CompetenceDTO competenceDTO, Long domaineCompetenceId);
    public CompetenceDTO updateCompetence(Long id, CompetenceDTO competenceDTO);
    public void deleteCompetence(Long id);
    public CompetenceDTO getCompetenceById(Long id);
    public List<CompetenceDTO> getAllCompetences();

    public CompetenceDTO assignCompetenceToProfile(Long competenceId, Long profileId, Integer niveau);



}
