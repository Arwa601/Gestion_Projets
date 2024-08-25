package com.Cp.Stage.DTOs;

import java.util.Date;

public class CertificationDTO {
    private Long id;
    private String nom;
    private String description;
    private Date date;
    private String avancement;
    private String piece_jointe;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getDate() {
        return this.date;
    }

    public String getAvancement() {
        return this.avancement;
    }

    public String getPiece_jointe() {
        return this.piece_jointe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAvancement(String avancement) {
        this.avancement = avancement;
    }

    public void setPiece_jointe(String piece_jointe) {
        this.piece_jointe = piece_jointe;
    }
}
