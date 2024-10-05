package com.Cp.Stage.Services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.CompetenceDTO;
import com.Cp.Stage.Models.Competence;
import com.Cp.Stage.Models.DomaineCompetence;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Repositories.CompetenceRepo;
import com.Cp.Stage.Repositories.DomaineCompetenceRepo;
import com.Cp.Stage.Repositories.ProfileRepo;
@Service
public class CompetenceServiceImpl implements CompetenceService{

    @Autowired
    private CompetenceRepo competenceRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private DomaineCompetenceRepo domaineCompetenceRepo;

    @Override
    public CompetenceDTO createCompetence(CompetenceDTO competenceDTO, Long domaineCompetenceId) {
        DomaineCompetence domaineCompetence = domaineCompetenceRepo.findById(domaineCompetenceId)
                .orElseThrow(() -> new RuntimeException("DomaineCompetence not found"));
        
        Competence competence = new Competence();
        competence.setTitre(competenceDTO.getTitre());
        competence.setNiveau(competenceDTO.getNiveau());
        competence.setDomaine_competence(domaineCompetence);
        
        Competence savedCompetence = competenceRepo.save(competence);
        return convertToDTO(savedCompetence);
    }

    @Override
    public CompetenceDTO updateCompetence(Long id, CompetenceDTO competenceDTO) {
        Competence competence = competenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Competence not found"));

        competence.setTitre(competenceDTO.getTitre());
        competence.setNiveau(competenceDTO.getNiveau());

        Competence updatedCompetence = competenceRepo.save(competence);
        return convertToDTO(updatedCompetence);
    }

    @Override
    public void deleteCompetence(Long id) {
        Competence competence = competenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Competence not found"));
        competenceRepo.delete(competence);
    }

    @Override
    public CompetenceDTO getCompetenceById(Long id) {
        Competence competence = competenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Competence not found"));
        return convertToDTO(competence);
    }

    @Override
    public List<CompetenceDTO> getAllCompetences() {
        List<Competence> competences = competenceRepo.findAll();
        return competences.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CompetenceDTO assignCompetenceToProfile(Long competenceId, Long profileId, Integer niveau) {
        Competence competence = competenceRepo.findById(competenceId)
                .orElseThrow(() -> new RuntimeException("Competence not found"));
        
        Profile profile = profileRepo.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        
        competence.getProfiles().add(profile);
        competence.setNiveau(niveau); 
        Competence updatedCompetence = competenceRepo.save(competence);
        
        return convertToDTO(updatedCompetence);
    }

    private CompetenceDTO convertToDTO(Competence competence) {
        CompetenceDTO dto = new CompetenceDTO();
        dto.setId(competence.getId());
        dto.setTitre(competence.getTitre());
        dto.setNiveau(competence.getNiveau());
        return dto;
    }
}
