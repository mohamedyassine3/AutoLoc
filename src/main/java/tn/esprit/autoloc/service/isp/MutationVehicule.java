package tn.esprit.autoloc.service.isp;

import tn.esprit.autoloc.domain.Vehicule;

public interface MutationVehicule {
    Vehicule ajouter(Vehicule vehicule);
    Vehicule modifier(Long id, Vehicule vehicule);
    void supprimer(Long id);
}