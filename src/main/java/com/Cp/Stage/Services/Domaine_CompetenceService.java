package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.Domaine_CompetenceDTO;

public interface Domaine_CompetenceService  {

    public String afficherDescription(String titre);
    public void updateTitre(Long id,String titre);
    public void updateDescription(Long id,String descrip);
    public void deleteDomaine(Long id);
    public void AjouterDomaine(Domaine_CompetenceDTO domaineCompetenceDTO);

}
