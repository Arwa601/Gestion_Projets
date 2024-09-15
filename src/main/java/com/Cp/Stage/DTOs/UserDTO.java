package com.Cp.Stage.DTOs;

import java.util.Date;

import com.Cp.Stage.Models.ERole;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String  nom;
    private String  nomUtilisateur ;
    private String  prenom;
    private String  email;
    private String  login;
    private String  password;
    private ERole   role;
    private Date date_integration;

    // public Long getId() {
    //     return this.id;
    // }

    // public String getNom() {
    //     return this.nom;
    // }

    // public String getPrenom() {
    //     return this.prenom;
    // }

    // public String getEmail() {
    //     return this.email;
    // }

    // public String getLogin() {
    //     return this.login;
    // }

    // public String getPassword() {
    //     return this.password;
    // }

    // public Role getRole() {
    //     return this.role;
    // }

    // public Date getDate_integration() {
    //     return this.date_integration;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public void setNom(String nom) {
    //     this.nom = nom;
    // }

    // public void setPrenom(String prenom) {
    //     this.prenom = prenom;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public void setLogin(String login) {
    //     this.login = login;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public void setRole(Role role) {
    //     this.role = role;
    // }

    // public void setDate_integration(Date date_integration) {
    //     this.date_integration = date_integration;
    // }
}
