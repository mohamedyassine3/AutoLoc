package tn.esprit.autoloc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public List<Vehicule> obtenirTousLesVehicules() {
        return vehiculeRepository.findAll();
    }

    @Override
    public List<Vehicule> obtenirVehiculesDisponibles() {
        return vehiculeRepository.findByDisponibleTrue();
    }

    @Override
    public Vehicule obtenirVehiculeParId(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {
        Vehicule existant = obtenirVehiculeParId(id);
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setPrixJour(vehicule.getPrixJour());
        existant.setDisponible(vehicule.getDisponible());
        return vehiculeRepository.save(existant);
    }

    @Override
    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }
}