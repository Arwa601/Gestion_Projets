package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.Avancement;
import com.Cp.Stage.Models.Certification;
import com.Cp.Stage.Models.Competence;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CompetenceRepo extends ListCrudRepository<Competence,Long> {
    List<Competence> findByprofil(String nom);
    Competence findByTitre(String titre);
}
