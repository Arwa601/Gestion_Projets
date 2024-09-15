package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.Cp.Stage.Models.Projet;

public interface ProjetRepo extends ListCrudRepository<Projet,Long> {

     // List<Projet> findByProfil(Long id_profil);
}
