package com.Cp.Stage.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Models.Projet;

@Repository
public interface DomaineCompetenceRepo extends JpaRepository<DomaineCompetence, Long> {

    List<DomaineCompetence> findByProjet(Projet projet);
    List<DomaineCompetence> findByProjetId(Long id);
    DomaineCompetence findByTitre(String titre);

}
