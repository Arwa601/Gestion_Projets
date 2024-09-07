package com.Cp.Stage.Repositories;

import com.Cp.Stage.Models.Profile;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ProfileRepo extends ListCrudRepository<Profile,Long> {



}
