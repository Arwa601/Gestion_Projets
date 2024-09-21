package com.Cp.Stage.Services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.Formation_SupportDTO;
import com.Cp.Stage.Models.FormationSupport;
import com.Cp.Stage.Repositories.FormationSupportRepo;

@Service

public class FormationSupportServiceImpl implements FormationSupportService{


    @Autowired
    private FormationSupportRepo formationSupportRepo;

    @Override
    public void AjouterFormation(Formation_SupportDTO formationSupportDTO){

        FormationSupport form= new FormationSupport();
        form.setId(formationSupportDTO.getId());
        form.setTitre(formationSupportDTO.getTitre());
        form.setLien_vers_formation(formationSupportDTO.getLien_vers_formation());
        formationSupportRepo.save(form) ;
    }
    @Override
    public void UpdateNomFormation(Long id,String nom){

        Optional<FormationSupport> optionalFormationSupport =formationSupportRepo.findById(id);
        optionalFormationSupport.ifPresent(form -> form.setTitre(nom));
    }
    @Override
    public void UpdateLienFormation(Long id,String lien){

        Optional<FormationSupport> optionalFormationSupport =formationSupportRepo.findById(id);
        optionalFormationSupport.ifPresent(form -> form.setLien_vers_formation(lien));
    }

    @Override
    public void deleteFichier(Long id){
        formationSupportRepo.deleteById(id);
    }
}
