package com.Cp.Stage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Certifications")

public class Certification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certif_id")
    private Long id;

    @Column
    private String nom;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    private Avancement avancement;

    @Column
    private String piece_jointe; //Path to file

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


}
