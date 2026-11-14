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
    @Operation(summary = "retrouver un véhicules", description = "Retourne un véhicules par id")

    @GetMapping("/{id}")
    public Vehicule getOne(@PathVariable Long id) {
        return vehiculeService.retrieve(id);
    }

    @Operation(summary = "Créer un véhicule")

    @PostMapping
    public  Vehicule  create(@RequestBody  Vehicule  v) {
        return vehiculeService.add(v);
    }
    @Operation( description = "modifier un véhicule")

    @PutMapping
    public  Vehicule  update(@RequestBody  Vehicule  v)  {
        return vehiculeService.modify(v);
    }
    @Operation(summary = "Supprimer un véhicule")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiculeService.remove(id);
    }

}
