package com.Cp.Stage.DTOs;
import lombok.Data;

@Data
public class RecommandationCoursDTO {
    private Long id;
    private String titre;
    private String lien_vers_formation;
    private Long projetId;
}
