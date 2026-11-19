package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.isp.ConsultationVehicule;
import tn.esprit.autoloc.service.tarification.TarificationService;

@Tag(name = "Tarification", description = "Calcul du prix d'une location (patron Strategy)")
@RestController
@RequestMapping("/tarifications")
@RequiredArgsConstructor
public class TarificationController {

    private final ConsultationVehicule consultationVehicule;
    private final TarificationService tarificationService;

    @Operation(summary = "Calculer le tarif d'une location",
            description = "Types : standard, weekend, longueDuree, entreprise")
    @GetMapping("/calculer/{vehiculeId}")
    public double calculer(@PathVariable Long vehiculeId,
                           @RequestParam int jours,
                           @RequestParam(defaultValue = "standard") String typeTarif) {
        Vehicule vehicule = consultationVehicule.consulter(vehiculeId);
        return tarificationService.calculerTarif(vehicule, jours, typeTarif);
    }
}