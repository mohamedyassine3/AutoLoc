package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.services.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    public Vehicule ajouter(@RequestBody Vehicule vehicule) {
        return vehiculeService.ajouterVehicule(vehicule);
    }

    @GetMapping
    public List<Vehicule> obtenirTous() {
        return vehiculeService.obtenirTousLesVehicules();
    }

    @GetMapping("/disponibles")
    public List<Vehicule> obtenirDisponibles() {
        return vehiculeService.obtenirVehiculesDisponibles();
    }

    @GetMapping("/{id}")
    public Vehicule obtenirParId(@PathVariable Long id) {
        return vehiculeService.obtenirVehiculeParId(id);
    }

    @PutMapping("/{id}")
    public Vehicule modifier(@PathVariable Long id, @RequestBody Vehicule vehicule) {
        return vehiculeService.modifierVehicule(id, vehicule);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
    }
}