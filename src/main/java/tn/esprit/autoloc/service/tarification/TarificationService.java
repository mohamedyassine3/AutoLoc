package tn.esprit.autoloc.service.tarification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Vehicule;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public double calculerTarif(Vehicule vehicule, int jours, String typeTarif) {
        if (jours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être strictement positif");
        }
        StrategieTarification strategie = strategies.get(typeTarif);
        if (strategie == null) {
            throw new IllegalArgumentException(
                    "Type de tarification inconnu : '" + typeTarif + "'. Types disponibles : " + strategies.keySet());
        }
        return strategie.calculer(vehicule.getTarifJournalier().doubleValue(), jours);
    }
}