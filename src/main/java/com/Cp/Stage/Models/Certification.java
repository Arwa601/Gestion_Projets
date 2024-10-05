package com.Cp.Stage.Models;

import java.util.Date;

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
    private String piece_jointe; 

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;


}
