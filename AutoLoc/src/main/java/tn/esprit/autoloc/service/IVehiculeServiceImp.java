package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@AllArgsConstructor
public class IVehiculeServiceImp implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ---------------------------------------------------------------------
    // Scheduled tasks
    // ---------------------------------------------------------------------
    /**
     * initialisation kol 5s (5000ms)..
     */
    @Scheduled(initialDelay = 5000, fixedRate = 60000)
    @Override
    public void actualiserEtatVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("=== [Q1] Actualisation de l'état des véhicules ===");
        System.out.println("Date et heure : " + timestamp);
        System.out.println("Nombre total de véhicules enregistrés : " + vehicules.size());
    }

    /**
     * kol thnin 9h, cron = seconds minutes hours ...
     */
    @Scheduled(cron = "0 0 9 * * MON")
    @Override
    public void genererRapportVehicules() {
        List<Vehicule> vehicules = vehiculeRepository.findAll();
        int totalVehicules = vehicules.size();
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.out.println("==================================================");
        System.out.println("[Q3] RAPPORT PÉRIODIQUE DES VÉHICULES");
        System.out.println("Date et heure de génération : " + timestamp);
        System.out.println("Nombre total de véhicules : " + totalVehicules);
        System.out.println("==================================================");
    }

    // ---------------------------------------------------------------------
    // CRUD operations 
    // ---------------------------------------------------------------------
    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> afficherTousVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule afficherVehiculeById(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}

