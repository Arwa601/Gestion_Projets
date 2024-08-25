package com.Cp.Stage.DTOs;

public class Formation_SupportDTO {

    private Long id;
    private String titre;
    private String lien_vers_formation;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLien_vers_formation() {
        return this.lien_vers_formation;
    }

    public void setLien_vers_formation(String lien_vers_formation) {
        this.lien_vers_formation = lien_vers_formation;
    }
}
