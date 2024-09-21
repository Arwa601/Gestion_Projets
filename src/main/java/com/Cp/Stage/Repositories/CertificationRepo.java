package com.Cp.Stage.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Avancement;
import com.Cp.Stage.Models.Certification;


//ListCrudRepository est une interface fournie
// par Spring Data JPA qui étend CrudRepository
@Repository
public interface CertificationRepo extends ListCrudRepository<Certification,Long> {
    List<Certification> findByAvancement(Avancement Avanc);
    Optional<Certification> findByNom(String Nom);
    List<Certification> findByProfileId(Long profilId);
    
}
