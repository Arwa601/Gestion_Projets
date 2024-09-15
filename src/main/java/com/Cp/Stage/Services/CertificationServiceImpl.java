package com.Cp.Stage.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.CertificationDTO;
import com.Cp.Stage.Models.Certification;
import com.Cp.Stage.Repositories.CertificationRepo;


@Service

public class CertificationServiceImpl implements CertificationService{


    @Autowired
    private CertificationRepo certifrepo;

    // @Override
    // public Avancement SuiviAvancement(String nom){
    //      return ((certifrepo.findByNom(nom)).getAvancement());
    // }


    // @Override
    // public Date Suivi_Date_prise_Certification(String nom){
    //     return ((certifrepo.findByNom(nom)).getDate());
    // }

    // @Override
    // public String AfficherDescription(String nom){
    //     return ((certifrepo.findByNom(nom)).getDescription());
    // }

    @Override
    public void  Ajoutercertif(CertificationDTO certificationDTO){
        Certification certif= new Certification();
        certif.setId(certificationDTO.getId());
        certif.setNom(certificationDTO.getNom());
        certif.setDescription(certificationDTO.getDescription());
        certif.setDate(certificationDTO.getDate());
        certif.setPiece_jointe(certificationDTO.getPiece_jointe());

        certifrepo.save(certif);
    }
}
