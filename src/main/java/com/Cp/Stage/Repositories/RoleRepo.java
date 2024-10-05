package com.Cp.Stage.Repositories;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.ERole;
import com.Cp.Stage.Models.Role;

@Repository
public interface RoleRepo extends  ListCrudRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}