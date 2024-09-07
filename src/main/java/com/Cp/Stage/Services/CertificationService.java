package com.Cp.Stage.Services;
import com.Cp.Stage.DTOs.CertificationDTO;
import com.Cp.Stage.Models.Avancement;


import java.util.Date;

public interface CertificationService {
    public Avancement SuiviAvancement(String nom);
    public Date Suivi_Date_prise_Certification(String nom);
    public String AfficherDescription(String nom);
    void Ajoutercertif(CertificationDTO certificationDTO);
}

