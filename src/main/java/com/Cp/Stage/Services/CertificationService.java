package com.Cp.Stage.Services;

import com.Cp.Stage.Models.Avancement;
import com.Cp.Stage.Repositories.CertificationRepo;

public interface CertificationService {
    Avancement SuiviAvancement(String nom);
}

