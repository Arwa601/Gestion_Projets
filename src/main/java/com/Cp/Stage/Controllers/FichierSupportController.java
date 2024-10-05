package com.Cp.Stage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Cp.Stage.DTOs.FichierSupportDTO;
import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.Models.FichierSupport;
import com.Cp.Stage.Services.FichierSupportService;

@RestController
@RequestMapping("/api/fichierSupport")
public class FichierSupportController {

    @Autowired
    private FichierSupportService fichierSupportService;

    @PostMapping("/create/{competenceId}")
    public ResponseEntity<?> createFichierSupport(
        @RequestParam("nom_fichier") String nom_fichier,
        @RequestParam("fichier") MultipartFile fichier,
        @PathVariable Long competenceId) throws Exception {
            FichierSupportDTO fichierSupportDTO = new FichierSupportDTO();
            fichierSupportDTO.setNom_fichier(nom_fichier);
            fichierSupportDTO.setFichier(fichier);

        return fichierSupportService.createFichierSupport(fichierSupportDTO, competenceId);
    }

    @GetMapping("/competence/{competenceId}")
    public ResponseEntity<?> getAllFichierSupports(@PathVariable Long competenceId) {
        return fichierSupportService.getFichierSupportByCompetenceId(competenceId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFichierSupportById(@PathVariable Long id) {
        FichierSupport fichierSupport = fichierSupportService.getFichierSupportById(id);
        if (fichierSupport == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Fichier support non trouvé."));
        }
        return ResponseEntity.ok(fichierSupport);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFichierSupport(@PathVariable Long id, 
                                                  @ModelAttribute FichierSupportDTO fichierSupportDTO) {
        return fichierSupportService.updateFichierSupport(id, fichierSupportDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFichierSupport(@PathVariable Long id) {
        return fichierSupportService.deleteFichierSupport(id);
    }
}
