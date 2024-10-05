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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600,methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api")
public class ProfileController {
    
    @Autowired
    ProfileService profileService;

@Operation(summary = "Get current user profile", description = "Returns the profile of the currently authenticated user.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Profile retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileDTO.class))),
    @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
    @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
})
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


@Operation(summary = "Get employees not assigned to any project", description = "Returns a list of employees not assigned to any project.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Employees retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileEmployeeDTO.class)))
})
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

@Operation(summary = "Get employees not assigned to any project", description = "Returns a list of employees not assigned to any project.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Employees retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileEmployeeDTO.class)))
})
@GetMapping("/admin/profile/employees/not-assigned")
public ResponseEntity<List<ProfileEmployeeDTO>> getEmployeesNotAssignedToAnyProject() {
    List<ProfileEmployeeDTO> employees = profileService.getEmployeesNotAssignedToAnyProject();
    return ResponseEntity.ok(employees);
}


@Operation(summary = "Get employees assigned to a project", description = "Returns a list of employees currently assigned to a project.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Employees retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileEmployeeDTO.class)))
})
@GetMapping("/admin/profile/employees/assigned")
public ResponseEntity<List<ProfileEmployeeDTO>> getEmployeesAssignedToProject() {
    List<ProfileEmployeeDTO> employees = profileService.getEmployeesAssignedToProject();
    return ResponseEntity.ok(employees);
}


@Operation(summary = "Get all employees", description = "Returns a list of all employees.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Employees retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileEmployeeDTO.class)))
})
@GetMapping("/admin/profile/employees")
public ResponseEntity<List<ProfileEmployeeDTO>> getAllEmployees() {
    List<ProfileEmployeeDTO> employees = profileService.getAllEmployees();
    return ResponseEntity.ok(employees);
}


@Operation(summary = "Get all managers", description = "Returns a list of all managers.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Managers retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileEmployeeDTO.class)))
})
@GetMapping("/admin/profile/managers")
public ResponseEntity<List<ProfileEmployeeDTO>> getAllManagers() {
    List<ProfileEmployeeDTO> managers = profileService.getAllManagers();
    return ResponseEntity.ok(managers);
}

@Operation(summary = "Get employees and manager for the current project", description = "Returns a list of employees and the manager for the current project assigned to the authenticated manager.")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Employees and manager retrieved successfully", content = @Content(mediaType = "application/json")),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
})
@GetMapping("/manager/current-project/employees")
public ResponseEntity<?> getEmployeesAndManagerForCurrentProject() {
        return profileService.getEmployeesAndManagerForCurrentProject();
    }

}


