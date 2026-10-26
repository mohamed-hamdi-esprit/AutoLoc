package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

// @Tag : Permet de regrouper le contrôleur sous une section claire et lisible dans Swagger UI
@Tag(name = "Gestion des Véhicules", description = "Endpoints pour la gestion complète des véhicules (Ajout, modification, affichage, suppression)")
@RestController
@AllArgsConstructor
@RequestMapping
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    // @Operation : Décrit clairement ce que fait cette méthode spécifique dans la documentation
    @Operation(
            summary = "Afficher tous les véhicules",
            description = "Récupère et retourne la liste de tous les véhicules enregistrés dans la base de données."
    )
    @GetMapping("/afficherTousVehicules")
    public List<Vehicule> getAllVehicules(){
        return vehiculeService.afficherTousVehicules();
    }

    @Operation(
            summary = "Ajouter un nouveau véhicule",
            description = "Permet d'enregistrer un véhicule en envoyant ses informations au format JSON."
    )
    @PostMapping("/ajouterVehicule")
    public Vehicule ajouterVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @Operation(
            summary = "Modifier un véhicule existant",
            description = "Met à jour les informations d'un véhicule en se basant sur les données envoyées dans le corps JSON."
    )
    @PutMapping("/modifierVehicule")
    public Vehicule modifierVehicule(@RequestBody Vehicule vehicule){
        return vehiculeService.modifierVehicule(vehicule);
    }

    @Operation(
            summary = "Supprimer un véhicule",
            description = "Supprime un véhicule de la base de données en spécifiant son identifiant unique (idVehicule) dans l'URL."
    )
    @DeleteMapping("/supprimerVehicule/{idVehicule}")
    public void supprimerVehicule(@PathVariable Long idVehicule){
        vehiculeService.supprimerVehicule(idVehicule);
    }
}