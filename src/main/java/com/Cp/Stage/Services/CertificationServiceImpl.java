package com.Cp.Stage.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Cp.Stage.DTOs.CertificationDTO;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.Models.Avancement;
import com.Cp.Stage.Models.Certification;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Repositories.CertificationRepo;

@Service
public class CertificationServiceImpl implements CertificationService{
    private static final Logger logger = LoggerFactory.getLogger(CertificationServiceImpl.class);

    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private CertificationRepo certifrepo;

    @Autowired
    private ProfileService  profileService;

    @Override
    public ResponseEntity<?> getCertifsByCurrentUserProfile() {
    try {
        ResponseEntity<?> response = profileService.getCurrentUserAndHisProfile();
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Object> currentUserAndProfile = (List<Object>) response.getBody();
            if (currentUserAndProfile != null && currentUserAndProfile.size() > 1) {
                Profile profile = (Profile) currentUserAndProfile.get(1);
                List<Certification> certifications = certifrepo.findByProfileId(profile.getId());
                return ResponseEntity.ok(certifications);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Erreur : Profil non trouvé"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Erreur lors de la récupération du profil"));
        }
    } catch (Exception e) {
        logger.error("Erreur lors de la récupération des certifications", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la récupération des certifications."));
    }
}

    @Override
    public ResponseEntity<?> addCertif(CertificationDTO certificationDTO) {
        try {
            Certification certification = new Certification();
            certification.setNom(certificationDTO.getNom());
            certification.setDescription(certificationDTO.getDescription());
            certification.setDate(certificationDTO.getDate());
            certification.setAvancement(Avancement.valueOf(certificationDTO.getAvancement()));
            MultipartFile file = certificationDTO.getFileCertif();
            if (file != null && !file.isEmpty()) {
                logger.info("Nom du fichier : {}", file.getOriginalFilename());
                logger.info("Taille du fichier : {}", file.getSize());

                // Save file and get file path
                String filePath = uploadDir + saveFile(file);
                certification.setPiece_jointe(filePath);
            }

            // Récupérer l'utilisateur courant et son profil
            ResponseEntity<?> response = profileService.getCurrentUserAndHisProfile();
            if (response.getStatusCode() == HttpStatus.OK) {
                List<Object> currentUserAndProfile = (List<Object>) response.getBody();
                if (currentUserAndProfile != null && currentUserAndProfile.size() > 1) {
                    Profile profileObject = (Profile) currentUserAndProfile.get(1);
                    certification.setProfile(profileObject);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Erreur : Profil non trouvé"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Erreur lors de la récupération du profil"));
            }

            certifrepo.save(certification);

            return ResponseEntity.ok(new MessageResponse("Certification ajoutée avec succès!"));

        } catch (IOException e) {
            logger.error("Erreur lors de la sauvegarde du fichier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la sauvegarde du fichier."));
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout de la certification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de l'ajout de la certification."));
        }
    }

    public String saveFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Le fichier est vide");
        }
        Path path = Paths.get(uploadDir + file.getOriginalFilename());

        // Enregistrer le fichier sur le disque
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return file.getOriginalFilename();
    }

    @Override
    public ResponseEntity<?> updateCertif(Long certifId, CertificationDTO certificationDTO) {
        try {
            Optional<Certification> existingCertifOpt = certifrepo.findById(certifId);
            if (existingCertifOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Erreur : Certification non trouvée"));
            }

            Certification existingCertif = existingCertifOpt.get();
            existingCertif.setNom(certificationDTO.getNom());
            existingCertif.setDescription(certificationDTO.getDescription());
            existingCertif.setDate(certificationDTO.getDate());
            existingCertif.setAvancement(Avancement.valueOf(certificationDTO.getAvancement()));


            MultipartFile file = certificationDTO.getFileCertif();
            if (file != null && !file.isEmpty()) {
                logger.info("Nom du fichier : {}", file.getOriginalFilename());
                logger.info("Taille du fichier : {}", file.getSize());

                // Save new file and set the path
                String filePath = uploadDir + saveFile(file);
                existingCertif.setPiece_jointe(filePath);
            }

            certifrepo.save(existingCertif);
            return ResponseEntity.ok(new MessageResponse("Certification modifiée avec succès!"));

        } catch (IOException e) {
            logger.error("Erreur lors de la modification de la certification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la modification de la certification."));
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout de la certification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la modification de la certification."));
        }
    }

    @Override
    public ResponseEntity<?> deleteCertif(Long certifId) {
    try {
        Optional<Certification> certificationOpt = certifrepo.findById(certifId);
        if (certificationOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Erreur : Certification non trouvée"));
        }

        certifrepo.deleteById(certifId);
        return ResponseEntity.ok(new MessageResponse("Certification supprimée avec succès!"));
    } catch (Exception e) {
        logger.error("Erreur lors de la suppression de la certification", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la suppression de la certification."));
    }
}


}
