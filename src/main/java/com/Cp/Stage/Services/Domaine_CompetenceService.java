package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.Domaine_CompetenceDTO;
import com.Cp.Stage.Repositories.Domaine_CompetenceRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface Domaine_CompetenceService  {

    public String afficherDescription(String titre);
    public void updateTitre(Long id,String titre);
    public void updateDescription(Long id,String descrip);
    public void deleteDomaine(Long id);
    public void AjouterDomaine(Domaine_CompetenceDTO domaineCompetenceDTO);

}
