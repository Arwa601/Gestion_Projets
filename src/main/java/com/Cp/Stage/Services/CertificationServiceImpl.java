package com.Cp.Stage.Services;

import com.Cp.Stage.Models.Avancement;
import com.Cp.Stage.Repositories.CertificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class CertificationServiceImpl implements CertificationService{


    @Autowired
    private CertificationRepo certifrepo;

    @Override
    public Avancement SuiviAvancement(String nom){

         return ((certifrepo.findByNom(nom)).getAvancement());
    }


}
