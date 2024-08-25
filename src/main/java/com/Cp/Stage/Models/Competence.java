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
    private List<Profile> profiles=new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "domaine_competence_id")
    private Domaine_Competence domaine_competence;

    @OneToMany(mappedBy = "competence")
    private List<Fichier_Support> fichiers_supports=new ArrayList<>();

    @OneToMany(mappedBy = "competence")
    private List<Formation_Support> formations_supports=new ArrayList<>();

}
