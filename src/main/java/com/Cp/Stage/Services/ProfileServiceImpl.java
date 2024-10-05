package com.Cp.Stage.Services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Exceptions.EntityNotFoundException;
import com.Cp.Stage.Models.Profile;
import com.Cp.Stage.Models.Projet;
import com.Cp.Stage.Models.User;
import com.Cp.Stage.Repositories.ProfileRepo;
import com.Cp.Stage.Repositories.ProjetRepo;
import com.Cp.Stage.Repositories.UserRepo;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProjetRepo projetRepo;

    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
@Override
public ProfileDTO getProfileCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        String username = authentication.getName();
        
        Optional<User> currentUser = userRepo.findFirstByUserName(username);
        
        if (currentUser != null) {
            Profile profileCurrentUser = currentUser.get().getProfile();
            
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

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Vérifier si l'utilisateur est authentifié et que ce n'est pas une authentification anonyme
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            
            Optional<User> currentUser = userRepo.findFirstByUserName(username);
            
            if (currentUser != null) {
                Profile currentProfile = currentUser.get().getProfile();
                
                if (currentProfile != null) {
                    currentProfile.setBrefDescription(profileDTO.getBrefDescription());
                    currentProfile.setCentreInteret(profileDTO.getCentreInteret());
                    currentProfile.setPointsForts(profileDTO.getPointsForts());
                    if (profileDTO.getPassword() != null && !profileDTO.getPassword().isEmpty()) {
                        currentUser.get().setPassword(passwordEncoder.encode(profileDTO.getPassword()));
                    }
                    profileRepo.save(currentProfile);
                    
                    return ResponseEntity.ok(new MessageResponse("Profile updated successfully!"));
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Profile not found"));
                }
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
        } catch (Exception e) {
            throw new RuntimeException("Error updating profile", e);
        }
        
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

@Override
public ResponseEntity<?> getCurrentUserAndHisProfile() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
        String username = authentication.getName();

        User currentUser = userRepo.findFirstByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Profile currentProfile = Optional.ofNullable(currentUser.getProfile())
            .orElseThrow(() -> new RuntimeException("Profile not found"));

        List<Object> userAndProfile = List.of(currentUser, currentProfile);
        return ResponseEntity.ok(userAndProfile);
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
}

static Profile unwrapProfil(Optional<Profile> entity, Long id) {
    if (entity.isPresent()) return entity.get();
    else throw new EntityNotFoundException(id, Profile.class);
}
    @Override
    public List<ProfileEmployeeDTO> getEmployeesNotAssignedToAnyProject() {
        List<Profile> employees = profileRepo.findAllEmployeesNotAssignedToAnyProject();
        return mapProfilesToDTOs(employees);
    }
    @Override
    public List<ProfileEmployeeDTO> getEmployeesAssignedToProject() {
        List<Profile> employees = profileRepo.findAllEmployeesAssignedToProject();
        Profile manager = employees.get(0).getProjet().getManager();
        return mapEmployeesAndManagerToDTO(employees, manager);
    }
    @Override
    public List<ProfileEmployeeDTO> getAllEmployees() {
        List<Profile> employees = profileRepo.findAllEmployees();
        return mapProfilesToDTOs(employees);
    }
    @Override
    public List<ProfileEmployeeDTO> getAllManagers() {
        List<Profile> managers = profileRepo.findAllManagers();
        return mapProfilesToDTOs(managers);
    }

    @Override
    public ResponseEntity<?> getEmployeesAndManagerForCurrentProject() {
        try {
            ResponseEntity<?> response = getCurrentUserAndHisProfile();
            if (response.getStatusCode() == HttpStatus.OK) {
                List<Object> currentUserAndProfile = (List<Object>) response.getBody();
                if (currentUserAndProfile != null && currentUserAndProfile.size() > 1) {
                    Profile currentManager = (Profile) currentUserAndProfile.get(1);
                    
                    Optional<Projet> managedProjectOpt = projetRepo.findByManager(currentManager);
                    if (managedProjectOpt == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Vous ne gérez aucun projet.");
                    }

                    List<Profile> employees = profileRepo.findEmployeesByProjectId(managedProjectOpt.get().getId());

                    List<ProfileEmployeeDTO> result = mapEmployeesAndManagerToDTO(employees, currentManager);
                    return ResponseEntity.ok(result);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur : Vous n'êtes pas autorisé.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des employés et du manager.");
        }
    }

    private List<ProfileEmployeeDTO> mapProfilesToDTOs(List<Profile> profiles) {
        return profiles.stream().map(profile -> {
            ProfileEmployeeDTO dto = new ProfileEmployeeDTO();
            dto.setNom(profile.getUser().getNom());
            dto.setUsername(profile.getUser().getUserName());
            dto.setEmail(profile.getUser().getEmail());
            dto.setBriefDescription(profile.getBrefDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    private List<ProfileEmployeeDTO> mapEmployeesAndManagerToDTO(List<Profile> employees, Profile manager) {
        List<ProfileEmployeeDTO> dtos = new ArrayList<>();

        if (manager != null) {
            ProfileEmployeeDTO managerDTO = new ProfileEmployeeDTO();
            managerDTO.setManagerNom(manager.getUser().getNom());
            managerDTO.setManagerUsername(manager.getUser().getUserName());
            managerDTO.setManagerEmail(manager.getUser().getEmail());
            managerDTO.setManagerBriefDescription(manager.getBrefDescription());
            dtos.add(managerDTO);
        
        }

        for (Profile employee : employees) {
            ProfileEmployeeDTO employeeDTO = new ProfileEmployeeDTO();
            employeeDTO.setNom(employee.getUser().getNom());
            employeeDTO.setUsername(employee.getUser().getUserName());
            employeeDTO.setEmail(employee.getUser().getEmail());
            employeeDTO.setBriefDescription(employee.getBrefDescription());

            dtos.add(employeeDTO);
        
        }

        return dtos;
    }

    @Override
    public Optional<Projet> getCurrentUserProject() throws Exception {
        ResponseEntity<?> response = getCurrentUserAndHisProfile();
        
        if (response.getStatusCode() == HttpStatus.OK) {
            List<Object> currentUserAndProfile = (List<Object>) response.getBody();
            
            if (currentUserAndProfile != null && currentUserAndProfile.size() > 1) {
                Profile profile = (Profile) currentUserAndProfile.get(1);
                
                Projet projet = profile.getProjet();
                if (projet != null) {
                    return Optional.of(projet);  
                }
            }
        }
        
        throw new Exception("Erreur : Profil ou Projet non trouvé");
    }
}
