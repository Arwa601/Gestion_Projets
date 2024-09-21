package com.Cp.Stage.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name="Profiles")

public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String brefDescription;
    @ElementCollection
    //kn mthothch y9olk vous essayez de déclarer un attribut @Basic avec un type conteneur

    private List<String> centreInteret;
    @ElementCollection
    private List<String> pointsForts ;

    // @Column(name = "photo_profile", nullable = true)
    // private String photo_profile;

    //whoever owns the foreign key column gets the @JoinColumn annotation.
    @OneToOne(mappedBy = "profile")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "profile")
    private List<Certification> certifications;

    @ManyToMany
    @JoinTable(name = "profile_competence",
        joinColumns = @JoinColumn(name = "profile_id"),
        inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competences;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = true)
    private Projet projet;

    @OneToOne(mappedBy = "manager")
    private Projet projetGere;



}
