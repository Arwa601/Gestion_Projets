package com.Cp.Stage.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.User;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findFirstByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
