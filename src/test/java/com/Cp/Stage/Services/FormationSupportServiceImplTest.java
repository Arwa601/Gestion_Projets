package com.Cp.Stage.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Cp.Stage.DTOs.Formation_SupportDTO;
import com.Cp.Stage.Models.FormationSupport;
import com.Cp.Stage.Repositories.FormationSupportRepo;

public class FormationSupportServiceImplTest {

    @InjectMocks
    private FormationSupportServiceImpl formationSupportService;

    @Mock
    private FormationSupportRepo formationSupportRepo;

    private FormationSupport formationSupport;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        formationSupport = new FormationSupport();
        formationSupport.setId(1L);
        formationSupport.setTitre("Test Formation");
        formationSupport.setLien_vers_formation("http://test.com");
    }

    @Test
    public void testAjouterFormation() {
        Formation_SupportDTO dto = new Formation_SupportDTO();
        dto.setId(1L);
        dto.setTitre("Test Formation");
        dto.setLien_vers_formation("http://test.com");

        when(formationSupportRepo.save(any(FormationSupport.class))).thenReturn(formationSupport);

        formationSupportService.AjouterFormation(dto);

        verify(formationSupportRepo, times(1)).save(any(FormationSupport.class));
    }

    @Test
    public void testUpdateNomFormation() {
        when(formationSupportRepo.findById(1L)).thenReturn(Optional.of(formationSupport));

        formationSupportService.UpdateNomFormation(1L, "Updated Formation");

        assertEquals("Updated Formation", formationSupport.getTitre());
    }

    @Test
    public void testUpdateLienFormation() {
        when(formationSupportRepo.findById(1L)).thenReturn(Optional.of(formationSupport));

        formationSupportService.UpdateLienFormation(1L, "http://updated.com");

        assertEquals("http://updated.com", formationSupport.getLien_vers_formation());
    }

    @Test
    public void testDeleteFichier() {
        doNothing().when(formationSupportRepo).deleteById(1L);

        formationSupportService.deleteFichier(1L);

        verify(formationSupportRepo, times(1)).deleteById(1L);
    }
}
