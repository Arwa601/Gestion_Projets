package com.Cp.Stage.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity  //pour indiquer que la classe est une entité JPA
@Table(name="Formation_Support")

public class Formation_Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String titre;

    @Column
    private String lien_vers_formation;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

}
