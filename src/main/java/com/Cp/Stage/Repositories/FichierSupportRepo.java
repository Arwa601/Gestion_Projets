package com.Cp.Stage.Repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.FichierSupport;
@Repository
public interface FichierSupportRepo extends ListCrudRepository<FichierSupport,Long> {

    // FichierSupport findByNom_fichier(String nom);    //à verifier
    List<FichierSupport> findByCompetenceId(Long competenceId);


}
