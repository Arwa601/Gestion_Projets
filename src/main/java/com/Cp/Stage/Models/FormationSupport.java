package com.Cp.Stage.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity  //pour indiquer que la classe est une entité JPA
@Table(name="Formation_Support")

public class FormationSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String titre;

    @Column
    private String lienVersFormation;

    @ManyToOne
    @JoinColumn(name = "competence_id")
    private Competence competence;

}
