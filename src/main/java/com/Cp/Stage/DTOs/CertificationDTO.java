package com.Cp.Stage.DTOs;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CertificationDTO {
    private Long id;
    private String nom;
    private String description;
    private Date date;
    private String avancement;
    private MultipartFile fileCertif;

}
