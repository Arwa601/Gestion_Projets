package com.Cp.Stage.Repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.Profile;


@Repository
public interface ProfileRepo extends ListCrudRepository<Profile,Long> {
    Optional<Profile> findById(Long id);

}
