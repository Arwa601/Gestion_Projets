package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Competence;
@Repository
public interface CompetenceRepo extends ListCrudRepository<Competence,Long> {
    // List<Comspetence> findByProfil(String nom);
    // Competence findByTitre(String titre);
}
