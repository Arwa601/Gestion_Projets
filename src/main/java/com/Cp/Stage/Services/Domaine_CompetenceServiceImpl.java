package com.Cp.Stage.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.Domaine_CompetenceDTO;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Repositories.DomaineCompetenceRepo;


@Service
public class Domaine_CompetenceServiceImpl implements Domaine_CompetenceService{

    @Autowired
    private DomaineCompetenceRepo domaine_CompetenceRepo;

    @Override
    public String afficherDescription(String titre){

        return (domaine_CompetenceRepo.findByTitre(titre)).getDescription();
    }
    @Override
    public void updateTitre(Long id,String titre){

        Optional<DomaineCompetence> optionalDomaine= domaine_CompetenceRepo.findById(id);
        optionalDomaine.ifPresent(domaineCompetence -> domaineCompetence.setTitre(titre));


    }
    @Override
    public void updateDescription(Long id,String descrip){

            Optional<DomaineCompetence> optionalDomaine = domaine_CompetenceRepo.findById(id);
            optionalDomaine.ifPresent(domaineCompetence -> domaineCompetence.setDescription(descrip));

     }
    @Override
    public void deleteDomaine(Long id){
        domaine_CompetenceRepo.deleteById(id);
    }

    @Override
    public void AjouterDomaine(Domaine_CompetenceDTO domaineCompetenceDTO){
        DomaineCompetence competence= new DomaineCompetence();
        competence.setId(domaineCompetenceDTO.getId());
        competence.setTitre(domaineCompetenceDTO.getTitre());
        competence.setDescription(domaineCompetenceDTO.getDescription());
        domaine_CompetenceRepo.save(competence);
    }
}
