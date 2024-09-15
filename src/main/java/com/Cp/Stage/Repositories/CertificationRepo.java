package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.Cp.Stage.Models.Certification;


//ListCrudRepository est une interface fournie
// par Spring Data JPA qui étend CrudRepository

public interface CertificationRepo extends ListCrudRepository<Certification,Long> {
    // List<Certification> findByAvancement(Avancement Avanc);
    //liste des certif non encore terminées ou terminées
    // Certification findByNom(String Nom);
}
