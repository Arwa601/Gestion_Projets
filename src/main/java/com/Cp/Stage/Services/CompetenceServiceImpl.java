package com.Cp.Stage.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.Models.Competence;
import com.Cp.Stage.Repositories.CompetenceRepo;
@Service
public class CompetenceServiceImpl implements CompetenceService {

    @Autowired
    private CompetenceRepo competenceRepo;

    // @Override
    // public String suiviNiveau(String titre){
    //     return (competenceRepo.findByTitre(titre)).getNiveau();
    // }
    // @Override

    // //En cas un ingenieur a amélioré une compétence
    // public void updateNiveau(String titre,String niv){

    //     (competenceRepo.findByTitre(titre)).setNiveau(niv);
    // }

    @Override
    public void AjouterCompetence(CompetenceDTO competenceDto){
        Competence competence= new Competence();
        competence.setId(competenceDto.getId());
        competence.setTitre(competenceDto.getTitre());
        competence.setNiveau(competenceDto.getNiveau());
        competenceRepo.save(competence);
    }

}
