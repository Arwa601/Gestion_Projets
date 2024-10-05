package com.Cp.Stage.DTOs;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FichierSupportDTO {

    private Long id;
    private String nom_fichier;
    private MultipartFile  fichier;

}
