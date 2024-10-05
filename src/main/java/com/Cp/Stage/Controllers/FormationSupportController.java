package com.Cp.Stage.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Cp.Stage.DTOs.ProfileDTO;
import com.Cp.Stage.DTOs.ProfileEmployeeDTO;
import com.Cp.Stage.Services.ProfileService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/FromationSupport")
public class FromationSupportController {

    @Autowired
    private FormationSupportService formationSupportService;


    @Operation(summary = "add a new formationSupport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "formationSupport added successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })

    @PostMapping("/ajouter")
    public ResponseEntity<String> ajouterFormation(@RequestBody Formation_SupportDTO formationSupportDTO) {
        formationSupportService.AjouterFormation(formationSupportDTO);
        return ResponseEntity.ok("Formation ajoutée avec succès !");
    }

    @Operation(summary = "update the name of an existing formationSupport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the name of formationSupport updated successfully"),
            @ApiResponse(responseCode = "404", description = "formationSupport not found", content = @Content(schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })
    @PutMapping("/updateNom/{id}")
    public ResponseEntity<String> updateNomFormation(@PathVariable Long id, @RequestParam String nom) {
        formationSupportService.UpdateNomFormation(id, nom);
        return ResponseEntity.ok("Nom de la formation mis à jour avec succès !");
    }
    @Operation(summary = "update the link of an existing formationSupport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "the link of formationSupport updated successfully"),
            @ApiResponse(responseCode = "404", description = "formationSupport not found", content = @Content(schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })
    @PutMapping("/updateLien/{id}")
    public ResponseEntity<String> updateLienFormation(@PathVariable Long id, @RequestParam String lien) {
        formationSupportService.UpdateLienFormation(id, lien);
        return ResponseEntity.ok("Lien vers la formation mis à jour avec succès !");
    }
    @Operation(summary = "delete an existing formationSupport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " formationSupport deleted successfully"),
            @ApiResponse(responseCode = "404", description = "formationSupport not found", content = @Content(schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = MessageResponse.class)))
    })

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<String> deleteFormation(@PathVariable Long id) {
        formationSupportService.deleteFichier(id);
        return ResponseEntity.ok("Formation supprimée avec succès !");
    }

}