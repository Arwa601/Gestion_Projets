package com.Cp.Stage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.Services.ProfileService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/profil")
public class ProfileController {
    
    @Autowired
    ProfileService profileService;
    
@GetMapping("/")
public ResponseEntity<ProfileDTO> getProfile() {
        try {
            ProfileDTO profile = profileService.getProfileCurrentUser();
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Unauthorized")) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            } else if (e.getMessage().contains("Profile not found")) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
@RequestMapping(value = "/update", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }
    
@PutMapping("/update")
public ResponseEntity<?> updateProfil(@RequestBody ProfileDTO profileDTO) {
    profileService.updateUserProfile(profileDTO);
    return new ResponseEntity<>(profileService.getProfileById(profileDTO.getId()), HttpStatus.OK);
}

}


