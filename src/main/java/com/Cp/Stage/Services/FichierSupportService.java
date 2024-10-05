package com.Cp.Stage.Services;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.FichierSupportDTO;
import com.Cp.Stage.Models.FichierSupport;

public interface FichierSupportService {
    public ResponseEntity<?> getFichierSupportByCompetenceId(Long competenceId);
    public ResponseEntity<?> createFichierSupport(FichierSupportDTO fichierSupportDTO, Long competenceId) throws Exception;
    public FichierSupport getFichierSupportById(Long id);
    public ResponseEntity<?> updateFichierSupport(Long id, FichierSupportDTO fichierSupportDTO);
    public ResponseEntity<?> deleteFichierSupport(Long id);

}
