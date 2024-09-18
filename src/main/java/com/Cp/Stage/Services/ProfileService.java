package com.Cp.Stage.Services;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProfileDTO;

public interface ProfileService {
    public ProfileDTO getProfileCurrentUser();
    public ResponseEntity<?> updateUserProfile(ProfileDTO profileDTO);
    public  ProfileDTO  getProfileById(Long id);
}
