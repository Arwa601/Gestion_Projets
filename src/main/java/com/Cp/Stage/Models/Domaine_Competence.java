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
@Table(name="Domaine_Competence")
public class Domaine_Competence {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String titre;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;


    @OneToMany(mappedBy = "domaine_competence")
    private List<Competence> competences=new ArrayList<>();;



}
