package com.Cp.Stage.DTOs;

import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {


    private String userName;

    private String email;

    private String password;

    private Set<String> roles;

  

}