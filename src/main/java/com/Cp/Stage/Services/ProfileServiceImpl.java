package com.Cp.Stage.Services;


import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Repositories.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public List<Profile> getProfiles() {
        return profileRepo.findAll();
    }
    @Override
    public List<String> AfficherCentresInteret(Long id) {
        return profileRepo.findById(id).get().getCentre_interet();
    }

    @Override
    public List<String> AfficherPointsForts(Long id) {
        return profileRepo.findById(id).get().getPoints_forts();
    }

    @Override
    public void AjouterProfil (ProfileDTO profileDTO){
        Profile profil=new Profile();
        profil.setId(profileDTO.getId());
        profil.setBref_description(profileDTO.getBref_description());
        profil.setPhoto_profile(profileDTO.getPhoto_profile());
        profil.setCentre_interet(profileDTO.getCentre_interet());
        profil.setPoints_forts(profileDTO.getPoints_forts());
        profileRepo.save(profil);

    }
    @Override
    public void deleteProfil (Long id){
        profileRepo.deleteById(id);
    }

    @Override
    public void updatePhotoProfile (Long id,String photo){
        profileRepo.findById(id).get().setPhoto_profile(photo);
    }

    @Override
    public void updateDescription (Long id,String descrip){
        profileRepo.findById(id).get().setBref_description(descrip);
    }


}
