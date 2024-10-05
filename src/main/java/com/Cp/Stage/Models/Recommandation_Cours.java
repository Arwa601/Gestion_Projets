package com.Cp.Stage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Recommandation_Cours")
public class Recommandation_Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String titre;

    @Column
    private String lien_vers_formation;


    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
}
