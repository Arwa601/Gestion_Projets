package com.Cp.Stage.Services;
import com.Cp.Stage.DTOs.Fichier_SupportDTO;
import com.Cp.Stage.Models.Fichier_Support;
import com.Cp.Stage.Repositories.Fichier_SupportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class Fichier_SupportServiceImpl implements Fichier_SupportService {

    @Autowired
    private Fichier_SupportRepo fichierSupportRepo;

    @Override
    public void AjouterFichier(Fichier_SupportDTO fichierSupportDTO){

        Fichier_Support fichierSupport= new Fichier_Support();
        fichierSupport.setId(fichierSupportDTO.getId());
        fichierSupport.setNom_fichier(fichierSupportDTO.getNom_fichier());
        fichierSupportRepo.save(fichierSupport) ;
    }
    @Override
    public void UpdateFichier(Long id,String nom){

        Optional<Fichier_Support> optionalFichier = fichierSupportRepo.findById(id);
        optionalFichier.ifPresent(fichierSupport -> fichierSupport.setNom_fichier(nom));
    }
    @Override
    public void deleteFichier(Long id){
        fichierSupportRepo.deleteById(id);
    }
}
