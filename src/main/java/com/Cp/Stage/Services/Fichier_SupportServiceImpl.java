package com.Cp.Stage.Services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.Fichier_SupportDTO;
import com.Cp.Stage.Models.FichierSupport;
import com.Cp.Stage.Repositories.FichierSupportRepo;

@Service
public class Fichier_SupportServiceImpl implements Fichier_SupportService {

    @Autowired
    private FichierSupportRepo fichierSupportRepo;

    @Override
    public void AjouterFichier(Fichier_SupportDTO fichierSupportDTO){

        FichierSupport fichierSupport= new FichierSupport();
        fichierSupport.setId(fichierSupportDTO.getId());
        fichierSupport.setNom_fichier(fichierSupportDTO.getNom_fichier());
        fichierSupportRepo.save(fichierSupport) ;
    }
    @Override
    public void UpdateFichier(Long id,String nom){

        Optional<FichierSupport> optionalFichier = fichierSupportRepo.findById(id);
        optionalFichier.ifPresent(fichierSupport -> fichierSupport.setNom_fichier(nom));
    }
    @Override
    public void deleteFichier(Long id){
        fichierSupportRepo.deleteById(id);
    }
}
