package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.Cp.Stage.Models.DomaineCompetence;

public interface DomaineCompetenceRepo extends ListCrudRepository<DomaineCompetence,Long> {

    // List<Domaine_Competence> findByprofil(String nom);
    DomaineCompetence findByTitre(String titre);

}
