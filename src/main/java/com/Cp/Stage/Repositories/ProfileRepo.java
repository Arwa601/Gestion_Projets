package com.Cp.Stage.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Projet;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Optional<Profile> findById(Long id);
    Optional<Profile> findByProjet(Projet projet);
    @Query("SELECT p FROM Profile p WHERE p.projet IS NULL AND p.user IN (SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_EMPLOYEE')")
    List<Profile> findAllEmployeesNotAssignedToAnyProject();


    @Query("SELECT p FROM Profile p WHERE p.projet IS NOT NULL AND p.user IN (SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_EMPLOYEE')")
    List<Profile> findAllEmployeesAssignedToProject();

    @Query("SELECT p FROM Profile p WHERE p.user IN (SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_EMPLOYEE')")
    List<Profile> findAllEmployees();



    @Query("SELECT p FROM Profile p WHERE p.user IN (SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_MANAGER')")
    List<Profile> findAllManagers();


    @Query("SELECT p FROM Profile p WHERE p.projet.id = :projectId AND p.user IN (SELECT u FROM User u JOIN u.roles r WHERE r.name = 'ROLE_EMPLOYEE')")
    List<Profile> findEmployeesByProjectId(@Param("projectId") Long projectId);

    
    @Query("SELECT p.manager FROM Projet p WHERE p.id = :projectId")
    Profile findManagerByProjectId(@Param("projectId") Long projectId);


}
