package com.Cp.Stage.Controllers;

import java.util.List;

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
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Services.ProfileService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class ProfileController {
    
    @Autowired
    ProfileService profileService;
    
@GetMapping("/profile")
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
@RequestMapping(value = "/profile/update", method = RequestMethod.OPTIONS)
public ResponseEntity<?> handleOptionsRequest() {
    return ResponseEntity.ok().build();
}
    
@PutMapping("/profile/update")
public ResponseEntity<?> updateProfil(@RequestBody ProfileDTO profileDTO) {
    try {
        profileService.updateUserProfile(profileDTO);
        ProfileDTO updatedProfile = profileService.getProfileById(profileDTO.getId());
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    } catch (RuntimeException e) {
        // Handle error with custom message
        return new ResponseEntity<>("Error updating profile: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


@GetMapping("/admin/profile/employees/not-assigned")
public ResponseEntity<List<ProfileEmployeeDTO>> getEmployeesNotAssignedToAnyProject() {
    List<ProfileEmployeeDTO> employees = profileService.getEmployeesNotAssignedToAnyProject();
    return ResponseEntity.ok(employees);
}

@GetMapping("/admin/profile/employees/assigned")
public ResponseEntity<List<ProfileEmployeeDTO>> getEmployeesAssignedToProject() {
    List<ProfileEmployeeDTO> employees = profileService.getEmployeesAssignedToProject();
    return ResponseEntity.ok(employees);
}

@GetMapping("/admin/profile/employees")
public ResponseEntity<List<ProfileEmployeeDTO>> getAllEmployees() {
    List<ProfileEmployeeDTO> employees = profileService.getAllEmployees();
    return ResponseEntity.ok(employees);
}

@GetMapping("/admin/profile/managers")
public ResponseEntity<List<ProfileEmployeeDTO>> getAllManagers() {
    List<ProfileEmployeeDTO> managers = profileService.getAllManagers();
    return ResponseEntity.ok(managers);
}

@GetMapping("/manager/current-project/employees")
public ResponseEntity<?> getEmployeesAndManagerForCurrentProject() {
        return profileService.getEmployeesAndManagerForCurrentProject();
    }

}


