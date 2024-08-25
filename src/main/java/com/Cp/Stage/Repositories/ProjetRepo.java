package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.Projet;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProjetRepo extends ListCrudRepository<Projet,Long> {

     List<Projet> findByProfil(Long id_profil);
}
