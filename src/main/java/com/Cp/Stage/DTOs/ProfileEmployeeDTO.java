package com.Cp.Stage.DTOs;

import lombok.Data;

@Data
public class ProfileEmployeeDTO {
    private String nom;
    private String username;
    private String email;
    private String briefDescription;

    // Manager info
    private String managerNom;
    private String managerUsername;
    private String managerEmail;
    private String managerBriefDescription;
}
