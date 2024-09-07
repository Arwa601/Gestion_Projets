package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.DTOs.Domaine_CompetenceDTO;
import com.Cp.Stage.Models.Competence;
import com.Cp.Stage.Models.Domaine_Competence;
import com.Cp.Stage.Repositories.Domaine_CompetenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class Domaine_CompetenceServiceImpl implements Domaine_CompetenceService{

    @Autowired
    private Domaine_CompetenceRepo domaine_CompetenceRepo;

    @Override
    public String afficherDescription(String titre){

        return (domaine_CompetenceRepo.findByTitre(titre)).getDescription();
    }
    @Override
    public void updateTitre(Long id,String titre){

        Optional<Domaine_Competence> optionalDomaine= domaine_CompetenceRepo.findById(id);
        optionalDomaine.ifPresent(domaineCompetence -> domaineCompetence.setTitre(titre));


    }
    @Override
    public void updateDescription(Long id,String descrip){

            Optional<Domaine_Competence> optionalDomaine = domaine_CompetenceRepo.findById(id);
            optionalDomaine.ifPresent(domaineCompetence -> domaineCompetence.setDescription(descrip));

     }
    @Override
    public void deleteDomaine(Long id){
        domaine_CompetenceRepo.deleteById(id);
    }

    @Override
    public void AjouterDomaine(Domaine_CompetenceDTO domaineCompetenceDTO){
        Domaine_Competence competence= new Domaine_Competence();
        competence.setId(domaineCompetenceDTO.getId());
        competence.setTitre(domaineCompetenceDTO.getTitre());
        competence.setDescription(domaineCompetenceDTO.getDescription());
        domaine_CompetenceRepo.save(competence);
    }
}
