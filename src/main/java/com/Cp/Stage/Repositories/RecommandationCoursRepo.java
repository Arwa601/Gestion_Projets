package com.Cp.Stage.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Models.RecommandationCours;

@Repository
public interface RecommandationCoursRepo extends JpaRepository<RecommandationCours, Long>  {
    List<RecommandationCours> findByProjet(Projet projet);
}
