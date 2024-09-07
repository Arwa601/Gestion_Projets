package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.Fichier_Support;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface Fichier_SupportRepo extends ListCrudRepository<Fichier_Support,Long> {

    Fichier_Support findByNom_fichier(String nom);    //à verifier
    List<Fichier_Support> findByCompetence(String nomComp);

}
