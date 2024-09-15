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
@Table(name="Competences")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String titre;

    @Column
    private String niveau;

    @ManyToMany(mappedBy = "competences")
    private List<Profile> profiles;

    @ManyToOne
    @JoinColumn(name = "domaine_competence_id")
    private DomaineCompetence domaine_competence;

    @JsonIgnore
    @OneToMany(mappedBy = "competence")
    private List<FichierSupport> fichiers_supports=new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "competence")
    private List<Formation_Support> formations_supports=new ArrayList<>();

}
