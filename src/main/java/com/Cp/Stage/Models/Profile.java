package com.Cp.Stage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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

    private String bref_description;
    @ElementCollection
    //kn mthothch y9olk vous essayez de déclarer un attribut @Basic avec un type conteneur

    private List<String> centre_interet;
    @ElementCollection
    private List<String> points_forts ;

    private String photo_profile;

    //whoever owns the foreign key column gets the @JoinColumn annotation.
    @OneToOne(mappedBy = "profile")
    private User user;

    @OneToMany(mappedBy = "profile")
    private List<Certification> certifications;

    @ManyToMany
    @JoinTable(name = "profile_competence",
        joinColumns = @JoinColumn(name = "profile_id"),
        inverseJoinColumns = @JoinColumn(name = "competence_id")
    )
    private List<Competence> competences;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;



}
