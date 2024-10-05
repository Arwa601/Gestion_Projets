package com.Cp.Stage.Services;

import org.springframework.http.ResponseEntity;

import com.Cp.Stage.DTOs.RecommandationCoursDTO;

public interface RecommandationCoursService {
    public ResponseEntity<?> addRecommandation(RecommandationCoursDTO recommandationCourDTO, Long projetId);
    public ResponseEntity<?> updateRecommandation(Long recomandationCourId, RecommandationCoursDTO recommandationCourDTO);
    public ResponseEntity<?> deleteRecommandation(Long recomandationCourId);
    public ResponseEntity<?> getRecommandationById(Long recomandationCourId);
    public ResponseEntity<?> getAllRecommandations();
}
