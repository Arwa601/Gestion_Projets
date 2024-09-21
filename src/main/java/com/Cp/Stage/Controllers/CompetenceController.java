package com.Cp.Stage.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.Services.CompetenceService;

@RestController
@RequestMapping("/api/competences")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @PostMapping("/manager/create/{domaineCompetenceId}")
    public ResponseEntity<CompetenceDTO> createCompetence(@PathVariable Long domaineCompetenceId, @RequestBody CompetenceDTO competenceDTO) {
        CompetenceDTO newCompetence = competenceService.createCompetence(competenceDTO, domaineCompetenceId);
        return new ResponseEntity<>(newCompetence, HttpStatus.CREATED);
    }

    @PutMapping("/manager/update/{id}")
    public ResponseEntity<CompetenceDTO> updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO competenceDTO) {
        CompetenceDTO updatedCompetence = competenceService.updateCompetence(id, competenceDTO);
        return new ResponseEntity<>(updatedCompetence, HttpStatus.OK);
    }

    @DeleteMapping("/manager/delete/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        competenceService.deleteCompetence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompetenceDTO>> getAllCompetences() {
        List<CompetenceDTO> competences = competenceService.getAllCompetences();
        return ResponseEntity.ok(competences);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDTO> getCompetenceById(@PathVariable Long id) {
        CompetenceDTO competence = competenceService.getCompetenceById(id);
        return ResponseEntity.ok(competence);
    }


    @PutMapping("/employee/assign/{competenceId}/{profileId}")
    public ResponseEntity<CompetenceDTO> assignCompetenceToProfile(@PathVariable Long competenceId, 
                                                                @PathVariable Long profileId, 
                                                                @RequestParam Integer niveau) {
        CompetenceDTO updatedCompetence = competenceService.assignCompetenceToProfile(competenceId, profileId, niveau);
        return ResponseEntity.ok(updatedCompetence);
    }

}
