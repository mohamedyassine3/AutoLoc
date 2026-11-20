package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EquipementRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.isp.ConsultationVehicule;
import tn.esprit.autoloc.service.isp.MutationVehicule;
import tn.esprit.autoloc.service.logging.JournalisationService;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService implements ICrudService<Vehicule, Long>, ConsultationVehicule, MutationVehicule {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeValidator vehiculeValidator;
    private final JournalisationService journalisationService;
    private final AgenceRepository agenceRepository;
    private final EquipementRepository equipementRepository;

    @Override
    public List<Vehicule> retrieveAll() {
        journalisationService.info("Récupération de tous les véhicules");
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule retrieve(Long id) {
        journalisationService.info("Récupération du véhicule id={}", id);
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable : " + id));
    }

    @Override
    public Vehicule add(Vehicule v) {
        vehiculeValidator.valider(v);
        journalisationService.info("Ajout du véhicule : {}", v.getImmatriculation());
        return vehiculeRepository.save(v);
    }

    @Override
    public Vehicule modify(Vehicule v) {
        vehiculeValidator.valider(v);
        journalisationService.info("Modification du véhicule id={}", v.getIdVehicule());
        return vehiculeRepository.save(v);
    }

    @Override
    public void remove(Long id) {
        journalisationService.info("Suppression du véhicule id={}", id);
        vehiculeRepository.deleteById(id);
    }

    @Override
    public Vehicule consulter(Long id) {
        return retrieve(id);
    }

    @Override
    public Vehicule ajouter(Vehicule vehicule) {
        return add(vehicule);
    }

    @Override
    public Vehicule modifier(Long id, Vehicule vehicule) {
        vehicule.setIdVehicule(id);
        return modify(vehicule);
    }

    @Override
    public void supprimer(Long id) {
        remove(id);
    }

    // ---------- Associations (TP : affectations) ----------

    /** Scénario 2 : un véhicule neuf est rattaché à une agence (ex. Sfax). */
    @Transactional
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = retrieve(idVehicule);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable : " + idAgence));
        vehicule.setAgence(agence);
        journalisationService.info("Véhicule {} affecté à l'agence {}", idVehicule, idAgence);
        return vehiculeRepository.save(vehicule);
    }

    /** Scénario 3 : l'agent installe un équipement (ex. siège bébé) sur le véhicule. */
    @Transactional
    public Vehicule affecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = retrieve(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable : " + idEquipement));
        if (vehicule.getEquipements().contains(equipement)) {
            throw new IllegalStateException("L'équipement " + idEquipement
                    + " est déjà installé sur le véhicule " + idVehicule);
        }
        vehicule.getEquipements().add(equipement);
        journalisationService.info("Équipement {} installé sur le véhicule {}", idEquipement, idVehicule);
        return vehiculeRepository.save(vehicule);
    }

    /** Scénario 3 : l'agent retire l'équipement au retour du véhicule. */
    @Transactional
    public Vehicule desaffecterEquipement(Long idVehicule, Long idEquipement) {
        Vehicule vehicule = retrieve(idVehicule);
        Equipement equipement = equipementRepository.findById(idEquipement)
                .orElseThrow(() -> new RuntimeException("Équipement introuvable : " + idEquipement));
        if (!vehicule.getEquipements().remove(equipement)) {
            throw new IllegalStateException("L'équipement " + idEquipement
                    + " n'est pas installé sur le véhicule " + idVehicule);
        }
        journalisationService.info("Équipement {} retiré du véhicule {}", idEquipement, idVehicule);
        return vehiculeRepository.save(vehicule);
    }
}