package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;
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
}