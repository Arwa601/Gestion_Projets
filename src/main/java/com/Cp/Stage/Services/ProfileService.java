package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.Models.Profile;

import java.util.List;

public interface ProfileService {

    public List<Profile> getProfiles();
    public List<String> AfficherCentresInteret(Long id);
    public List<String> AfficherPointsForts(Long id);
    public void AjouterProfil (ProfileDTO profileDTO);
    public void deleteProfil (Long id);
    public void updatePhotoProfile (Long id,String photo);
    public void updateDescription (Long id,String descrip);

}
