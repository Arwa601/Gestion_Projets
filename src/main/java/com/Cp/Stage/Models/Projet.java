package com.Cp.Stage.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Projets")

public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nom;
    @Column
    private String details_projet;

    @OneToMany(mappedBy = "projet")
    private List<Profile> profiles;

    @OneToMany(mappedBy = "projet")
    private List<Domaine_Competence> domaines_competences;

    @OneToMany(mappedBy = "projet")
    private List<Recommandation_Cours> recommandations_cours;
}
