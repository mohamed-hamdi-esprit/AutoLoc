package tn.esprit.autoloc.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.repository.AgenceRepository;

@Service
@AllArgsConstructor
public class AgenceService implements IAgenceService {

    private final AgenceRepository agenceRepository;

    @Scheduled(fixedDelay = 120000)
    public void suivreNombreAgences() {
        List<Agence> agences = agenceRepository.findAll();
        System.out.println("[Suivi] Total agences : " + agences.size());
        System.out.println("[Suivi] Date/Heure : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        if (agence.getIdAgence() == null || !agenceRepository.existsById(agence.getIdAgence())) {
            throw new IllegalArgumentException("Agence introuvable : " + agence.getIdAgence());
        }
        return agenceRepository.save(agence);
    }

    @Override
    public List<Agence> afficherToutesAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence afficherAgenceById(Long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerAgence(Long id) {
        agenceRepository.deleteById(id);
    }
}
