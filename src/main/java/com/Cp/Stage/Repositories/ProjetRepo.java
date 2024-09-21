package com.Cp.Stage.Repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Projet;


@Repository
public interface ProjetRepo extends ListCrudRepository<Projet,Long> {

     Optional<Projet> findByProfiles(Profile profile);
     Optional<Projet> findByManager(Profile manger);
}
