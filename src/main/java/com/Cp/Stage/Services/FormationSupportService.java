package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.Formation_SupportDTO;

public interface FormationSupportService {
    public void AjouterFormation(Formation_SupportDTO formationSupportDTO);
    public void UpdateNomFormation(Long id,String nom);
    public void UpdateLienFormation(Long id,String lien);
    public void deleteFichier(Long id);
}
