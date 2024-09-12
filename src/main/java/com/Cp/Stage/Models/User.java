package com.Cp.Stage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String nom;

    private String  prenom;

    @Column(name = "user_email", nullable = false, unique = true)
    private String  nomUtilisateur;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    private String  login;

    private String password;

    private Role role;

    private Date date_integration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

}
