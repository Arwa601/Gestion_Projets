package com.Cp.Stage.Repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.Cp.Stage.Models.User;


@Repository
public interface UserRepo extends ListCrudRepository<User,Long> {

    User findFirstByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
