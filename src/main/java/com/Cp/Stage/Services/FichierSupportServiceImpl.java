package com.Cp.Stage.Services;
import java.io.IOException;
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

import com.Cp.Stage.DTOs.FichierSupportDTO;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.Models.Competence;
import com.Cp.Stage.Models.FichierSupport;
import com.Cp.Stage.Repositories.CompetenceRepo;
import com.Cp.Stage.Repositories.FichierSupportRepo;

@Service
public class FichierSupportServiceImpl implements FichierSupportService {
    private static final Logger logger = LoggerFactory.getLogger(CertificationServiceImpl.class);

    @Autowired
    private FichierSupportRepo fichierSupportRepo;

    @Autowired
    private CertificationServiceImpl  certifServiceImp;

    @Autowired
    CompetenceRepo  competenceRepo;

    @Value("${file.upload-dir-support-file}")
    private String uploadDirSupportFile;

    @Override
    public ResponseEntity<?> createFichierSupport(FichierSupportDTO fichierSupportDTO, Long competenceId) throws Exception {
        try {
            Optional<Competence> competenceOpt = competenceRepo.findById(competenceId);
            if (!competenceOpt.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Competence not found."));
            }
    
            FichierSupport fichierSupport = new FichierSupport();
            fichierSupport.setNom_fichier(fichierSupportDTO.getNom_fichier());
            fichierSupport.setCompetence(competenceOpt.get());
    
            MultipartFile file = fichierSupportDTO.getFichier();
            if (file != null && !file.isEmpty()) {
                logger.info("Nom du fichier : {}", file.getOriginalFilename());
                logger.info("Taille du fichier : {}", file.getSize());
    
                String filePath = uploadDirSupportFile+"/" + certifServiceImp.saveFile(file);
                fichierSupport.setFichier(filePath); // Set the file path on the FichierSupport entity
            }
    
            fichierSupportRepo.save(fichierSupport);
            return ResponseEntity.ok(new MessageResponse("Fichier support ajouté avec succès!"));
            
        } catch (IOException e) {
            logger.error("Erreur lors de la sauvegarde du fichier", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la sauvegarde du fichier."));
            
        } catch (Exception e) {
            logger.error("Erreur lors de l'ajout du fichier support", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de l'ajout du fichier support."));
        }
    }
    


    @Override
public ResponseEntity<?> getFichierSupportByCompetenceId(Long competenceId) {
    List<FichierSupport> fichierSupports = fichierSupportRepo.findByCompetenceId(competenceId);
    
    if (fichierSupports.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Aucun fichier support trouvé pour cette compétence."));
    }
    
    return ResponseEntity.ok(fichierSupports);
}

    @Override
    public FichierSupport getFichierSupportById(Long id) {
        return fichierSupportRepo.findById(id).orElse(null);
    }

    @Override
public ResponseEntity<?> updateFichierSupport(Long id, FichierSupportDTO fichierSupportDTO) {
    Optional<FichierSupport> fichierSupportOpt = fichierSupportRepo.findById(id);

    if (!fichierSupportOpt.isPresent()) {
        return ResponseEntity.badRequest().body(new MessageResponse("Fichier support non trouvé."));
    }

    FichierSupport fichierSupport = fichierSupportOpt.get();
    fichierSupport.setNom_fichier(fichierSupportDTO.getNom_fichier());

    MultipartFile file = fichierSupportDTO.getFichier();
    if (file != null && !file.isEmpty()) {
        try {
            String filePath = uploadDirSupportFile + certifServiceImp.saveFile(file);
            fichierSupport.setFichier(filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Erreur lors de la mise à jour du fichier."));
        }
    }

    fichierSupportRepo.save(fichierSupport);
    return ResponseEntity.ok(new MessageResponse("Fichier support mis à jour avec succès!"));
}


    

    @Override
    public ResponseEntity<?> deleteFichierSupport(Long id) {
        Optional<FichierSupport> fichierSupportOpt = fichierSupportRepo.findById(id);

        if (!fichierSupportOpt.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Fichier support non trouvé."));
        }

        fichierSupportRepo.delete(fichierSupportOpt.get());
        return ResponseEntity.ok(new MessageResponse("Fichier support supprimé avec succès!"));
    }
}
