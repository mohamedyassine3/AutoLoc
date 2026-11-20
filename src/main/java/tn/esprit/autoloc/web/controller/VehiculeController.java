package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.VehiculeService;

import java.util.List;

@Tag(name = "Véhicules", description = "Gestion des véhicules")
@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @Operation(summary = "Lister les véhicules", description = "Retourne tous les véhicules")
    @GetMapping
    public List<Vehicule> getAll() {
        return vehiculeService.retrieveAll();
    }

    @Operation(summary = "Retrouver un véhicule", description = "Retourne un véhicule par id")
    @GetMapping("/{id}")
    public Vehicule getOne(@PathVariable Long id) {
        return vehiculeService.retrieve(id);
    }

    @Operation(summary = "Créer un véhicule")
    @PostMapping
    public Vehicule create(@RequestBody Vehicule v) {
        return vehiculeService.add(v);
    }

    @Operation(summary = "Modifier un véhicule")
    @PutMapping
    public Vehicule update(@RequestBody Vehicule v) {
        return vehiculeService.modify(v);
    }

    @Operation(summary = "Supprimer un véhicule")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiculeService.remove(id);
    }

    @Operation(summary = "Affecter un véhicule à une agence")
    @PutMapping("/{idVehicule}/agence/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule, @PathVariable Long idAgence) {
        return vehiculeService.affecterVehicule(idVehicule, idAgence);
    }

    @Operation(summary = "Installer un équipement sur un véhicule")
    @PutMapping("/{idVehicule}/equipements/{idEquipement}")
    public Vehicule affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.affecterEquipement(idVehicule, idEquipement);
    }

    @Operation(summary = "Retirer un équipement d'un véhicule")
    @DeleteMapping("/{idVehicule}/equipements/{idEquipement}")
    public Vehicule desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return vehiculeService.desaffecterEquipement(idVehicule, idEquipement);
    }
}