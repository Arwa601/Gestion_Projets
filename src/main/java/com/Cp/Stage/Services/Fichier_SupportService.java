package com.Cp.Stage.Services;

import com.Cp.Stage.DTOs.Fichier_SupportDTO;

public interface Fichier_SupportService {
    public void AjouterFichier(Fichier_SupportDTO fichierSupportDTO);
    public void UpdateFichier(Long id,String nom);
    public void deleteFichier(Long id);
}
