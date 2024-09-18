package com.Cp.Stage.Services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cp.Stage.DTOs.MessageResponse;
import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.Exceptions.EntityNotFoundException;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.ProfileRepo;
import com.Cp.Stage.Repositories.UserRepo;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserRepo  userRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
@Override
public ProfileDTO getProfileCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        String username = authentication.getName();
        
        User currentUser = userRepo.findFirstByUserName(username);
        
        if (currentUser != null) {
            Profile profileCurrentUser = currentUser.getProfile();
            
            if (profileCurrentUser != null) {
                return new ProfileDTO(
                    profileCurrentUser.getId(),
                    profileCurrentUser.getBrefDescription(),
                    profileCurrentUser.getCentreInteret(),
                    profileCurrentUser.getPointsForts()
                );
            } else {
                throw new RuntimeException("Profile not found for the current user.");
            }
        } else {
            throw new RuntimeException("User not found.");
        }
    }
    
    throw new RuntimeException("Unauthorized");
}

@Override
public ResponseEntity<?> updateUserProfile(ProfileDTO profileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'utilisateur est authentifié et que ce n'est pas une authentification anonyme
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            
            User currentUser = userRepo.findFirstByUserName(username);
            
            if (currentUser != null) {
                Profile currentProfile = currentUser.getProfile();
                
                if (currentProfile != null) {
                    currentProfile.setBrefDescription(profileDTO.getBrefDescription());
                    currentProfile.setCentreInteret(profileDTO.getCentreInteret());
                    currentProfile.setPointsForts(profileDTO.getPointsForts());
                    if (profileDTO.getPassword() != null && !profileDTO.getPassword().isEmpty()) {
                        currentUser.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
                    }
                    profileRepo.save(currentProfile);
                    
                    return ResponseEntity.ok(new MessageResponse("Profile updated successfully!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Profile not found"));
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
}

public ProfileDTO getProfileById(Long id) {
    Optional<Profile> profileOpt = profileRepo.findById(id);
    Profile unwrapedProfil = ProfileServiceImpl.unwrapProfil(profileOpt, id);
        return new ProfileDTO(
            unwrapedProfil.getId(),
            unwrapedProfil.getBrefDescription(),
            unwrapedProfil.getCentreInteret(),
            unwrapedProfil.getPointsForts()
        );

}


static Profile unwrapProfil(Optional<Profile> entity, Long id) {
    if (entity.isPresent()) return entity.get();
    else throw new EntityNotFoundException(id, Profile.class);
}
}
