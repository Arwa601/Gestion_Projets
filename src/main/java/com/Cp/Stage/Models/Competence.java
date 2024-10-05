package com.Cp.Stage.Models;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="competences")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="competence_id")
    private Long id;

    @Column
    private String titre;

    @Column
    private Integer niveau;

    @ManyToMany(mappedBy = "competences")
    private List<Profile> profiles;

    @ManyToOne
    @JoinColumn(name = "domaineCompetence_id")
    private DomaineCompetence domaineCompetence;

    @JsonIgnore
    @OneToMany(mappedBy = "competences")
    private List<FichierSupport> fichiers_supports=new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "competences")
    private List<FormationSupport> formations_supports=new ArrayList<>();

}
