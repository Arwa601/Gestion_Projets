package com.Cp.Stage.DTOs;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;

    private String brefDescription;

    private List<String> centreInteret;

    private List<String> pointsForts;

    @JsonIgnore
    private String  password;

    public ProfileDTO(Long id,String brefDescription, List<String> centreInteret, List<String> pointsForts ){
        this.id=id;
        this.brefDescription=brefDescription;
        this.centreInteret=centreInteret;
        this.pointsForts=pointsForts;
    }

    
}
