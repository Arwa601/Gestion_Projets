package com.Cp.Stage.Services;
import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.CertificationDTO;

public interface CertificationService {
    public ResponseEntity<?> getCertifsByCurrentUserProfile();
    public ResponseEntity<?> addCertif(CertificationDTO certificationDTO);
    public ResponseEntity<?> updateCertif(Long certifId, CertificationDTO certificationDTO);
    public ResponseEntity<?> deleteCertif(Long certifId);
    // public ResponseEntity<?> updateCertif(CertificationDTO certificationDTO);
    // public CertificationDTO getCertifConnectedUser(Long profileId);
    // public CertificationDTO getCertifByNom(String nom);
    // public CertificationDTO getCertifByEtatAvancement(Avancement avancement);


}

