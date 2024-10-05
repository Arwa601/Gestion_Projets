package com.Cp.Stage.Services;


import com.Cp.Stage.DTOs.SignupRequest;
import com.Cp.Stage.DTOs.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signuprequest);

}


