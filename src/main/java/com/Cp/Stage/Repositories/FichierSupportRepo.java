package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.Cp.Stage.Models.FichierSupport;

public interface FichierSupportRepo extends ListCrudRepository<FichierSupport,Long> {

    // FichierSupport findByNom_fichier(String nom);    //à verifier
    // List<FichierSupport> findByCompetence(String nomComp);

}
