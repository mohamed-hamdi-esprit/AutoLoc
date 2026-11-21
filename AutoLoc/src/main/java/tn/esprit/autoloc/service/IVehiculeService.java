package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;
import java.util.List;

/**
 * Service interface for {@link Vehicule} operations.
 * Includes CRUD methods and the scheduled tasks required by the assignment.
 */
public interface IVehiculeService {
    // CRUD operations
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    List<Vehicule> afficherTousVehicules();
    Vehicule afficherVehiculeById(Long id);
    void supprimerVehicule(Long id);

    // Scheduled tasks
    void actualiserEtatVehicules();
    void genererRapportVehicules();
}
