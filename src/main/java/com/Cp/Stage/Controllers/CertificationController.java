package com.Cp.Stage.Controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Cp.Stage.DTOs.CertificationDTO;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.Services.CertificationService;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @GetMapping("/")
public ResponseEntity<?> getUserCertifications() {
    try {
        return certificationService.getCertifsByCurrentUserProfile();
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la récupération des certifications."));
    }
}


    @PostMapping("/add")
    public ResponseEntity<?> addCertification(
        @RequestParam("nom") String nom,
        @RequestParam("description") String description,
        @RequestParam("date") Date date,
        @RequestParam("avancement") String avancement,
        @RequestParam("fileCertif") MultipartFile fileCertif
    ) {
        try {
            CertificationDTO certificationDTO = new CertificationDTO();
            certificationDTO.setNom(nom);
            certificationDTO.setDescription(description);
            certificationDTO.setDate(date);
            certificationDTO.setAvancement(avancement);
            certificationDTO.setFileCertif(fileCertif);

            return certificationService.addCertif(certificationDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de l'ajout de la certification."));
        }
    }

    @PutMapping("/update/{id}")
public ResponseEntity<?> updateCertification(
    @PathVariable Long id,
    @RequestParam("nom") String nom,
    @RequestParam("description") String description,
    @RequestParam("date") Date date,
    @RequestParam("avancement") String avancement,
    @RequestParam("fileCertif") MultipartFile fileCertif
) {
    try {
        CertificationDTO certificationDTO = new CertificationDTO();
        certificationDTO.setNom(nom);
        certificationDTO.setDescription(description);
        certificationDTO.setDate(date);
        certificationDTO.setAvancement(avancement);
        certificationDTO.setFileCertif(fileCertif);

        return certificationService.updateCertif(id, certificationDTO);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la modification de la certification."));
    }
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteCertification(@PathVariable Long id) {
    try {
        return certificationService.deleteCertif(id);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la suppression de la certification."));
    }
}


}
