package com.Cp.Stage.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Models.Projet;

public interface ProfileService {
    public ProfileDTO getProfileCurrentUser();
    public ResponseEntity<?> updateUserProfile(ProfileDTO profileDTO);
    public  ProfileDTO  getProfileById(Long id);
    public List<ProfileEmployeeDTO> getEmployeesNotAssignedToAnyProject();
    public List<ProfileEmployeeDTO> getEmployeesAssignedToProject();
    public List<ProfileEmployeeDTO> getAllEmployees();
    public List<ProfileEmployeeDTO> getAllManagers();
    public ResponseEntity<?> getEmployeesAndManagerForCurrentProject();

    public ResponseEntity<?> getCurrentUserAndHisProfile();
    public Optional<Projet> getCurrentUserProject() throws Exception;
}
