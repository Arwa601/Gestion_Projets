package com.Cp.Stage.Models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToOne
    @JoinColumn(name = "manager_profile_id", referencedColumnName = "id")
    private Profile manager;

    @OneToMany(mappedBy = "projet")
    private List<DomaineCompetence> domaines_competences;

    @OneToMany(mappedBy = "projet")
    private List<RecommandationCours> recommandations_cours;
}
