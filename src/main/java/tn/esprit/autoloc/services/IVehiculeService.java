package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Vehicule;
import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    List<Vehicule> obtenirTousLesVehicules();
    List<Vehicule> obtenirVehiculesDisponibles();
    Vehicule obtenirVehiculeParId(Long id);
    Vehicule modifierVehicule(Long id, Vehicule vehicule);
    void supprimerVehicule(Long id);
}