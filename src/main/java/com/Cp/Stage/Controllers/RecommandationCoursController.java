package com.Cp.Stage.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.RecommandationCoursDTO;
import com.Cp.Stage.Services.RecommandationCoursServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recommandations")
@Validated
public class RecommandationCoursController {

    @Autowired
    private RecommandationCoursServiceImpl  recommandationCoursService;

    @PostMapping("/add")
    public ResponseEntity<?> addRecommandationCours(@Valid @RequestBody RecommandationCoursDTO recommandationCoursDTO) {
        return recommandationCoursService.addRecommandationCours(recommandationCoursDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRecommandationCours(
            @PathVariable("id") Long recommandationId,
            @Valid @RequestBody RecommandationCoursDTO recommandationCoursDTO) {
        return recommandationCoursService.updateRecommandationCours(recommandationId, recommandationCoursDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRecommandationCours(@PathVariable("id") Long recommandationId) {
        return recommandationCoursService.deleteRecommandationCours(recommandationId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecommandationById(@PathVariable("id") Long recommandationId) {
        return recommandationCoursService.getRecommandationById(recommandationId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRecommandationsForProject() {
        return recommandationCoursService.getAllRecommandationsForProject();
    }
}