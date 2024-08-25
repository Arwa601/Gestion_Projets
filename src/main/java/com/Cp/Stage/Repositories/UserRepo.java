package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepo extends ListCrudRepository<User,Long> {

    User findFirstByEmail(String email);
}
