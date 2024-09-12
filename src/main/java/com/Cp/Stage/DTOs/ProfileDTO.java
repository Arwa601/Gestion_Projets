package com.Cp.Stage.DTOs;

import java.util.List;
import lombok.Data;

@Data
public class ProfileDTO {
    private Long id;

    private String bref_description;


    private List<String> centre_interet;

    private List<String> points_forts ;

    private String photo_profile;

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public String getBref_description() {
    //     return this.bref_description;
    // }

    // public void setBref_description(String bref_description) {
    //     this.bref_description = bref_description;
    // }

    // public List<String> getCentre_interet() {
    //     return this.centre_interet;
    // }

    // public void setCentre_interet(List<String> centre_interet) {
    //     this.centre_interet = centre_interet;
    // }

    // public List<String> getPoints_forts() {
    //     return this.points_forts;
    // }

    // public void setPoints_forts(List<String> points_forts) {
    //     this.points_forts = points_forts;
    // }

    // public String getPhoto_profile() {
    //     return this.photo_profile;
    // }

    // public void setPhoto_profile(String photo_profile) {
    //     this.photo_profile = photo_profile;
    // }
}
