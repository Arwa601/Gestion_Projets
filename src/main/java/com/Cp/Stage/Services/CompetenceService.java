package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.CompetenceDTO;

public interface CompetenceService {
    public String suiviNiveau(String titre);
    public void updateNiveau(String titre,String niv);
    public void AjouterCompetence(CompetenceDTO competenceDto);
}
