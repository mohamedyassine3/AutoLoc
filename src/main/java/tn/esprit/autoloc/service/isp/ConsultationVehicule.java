package tn.esprit.autoloc.service.isp;

import tn.esprit.autoloc.domain.Vehicule;

public interface ConsultationVehicule {
    Vehicule consulter(Long id);
}