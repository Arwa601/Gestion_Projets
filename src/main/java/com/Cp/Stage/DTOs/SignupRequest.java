package com.Cp.Stage.DTOs;

import java.util.Date;
import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {


    private String userName;

    private String email;

    private String password;

    private Set<String> roles;

    private String nom; 

    private String prenom; 

    private Date dateIntegration;



}