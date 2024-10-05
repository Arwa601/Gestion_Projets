package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.Domaine_Competence;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface Domaine_CompetenceRepo extends ListCrudRepository<Domaine_Competence,Long> {

    List<Domaine_Competence> findByprofil(String nom);
    Domaine_Competence findByTitre(String titre);

}
