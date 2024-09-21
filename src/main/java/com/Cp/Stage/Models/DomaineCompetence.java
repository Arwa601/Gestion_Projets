package com.Cp.Stage.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="Domaine_Competence")
public class DomaineCompetence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String titre;

    @Column
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @JsonIgnore
    @OneToMany(mappedBy = "domaine_competence")
    private List<Competence> competences;



}
