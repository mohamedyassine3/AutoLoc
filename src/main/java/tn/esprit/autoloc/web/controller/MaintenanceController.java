package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.service.MaintenanceService;

import java.util.List;
@Tag(name = "Maintenances", description = "Gestion des Maintenance")

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    @Operation(summary = "Lister les Maintenances ", description = "Retourne tous les maintenances")

    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.retrieveAll();
    }
    @Operation(summary = "Retourner Maintenances ", description = "retourne Maintenances")


    @GetMapping("/{id}")
    public Maintenance getOne(@PathVariable Long id) {
        return maintenanceService.retrieve(id);
    }
    @Operation(summary = "Ajouter Maintenances ", description = "Ajoute Maintenances")

    @PostMapping
    public Maintenance create(@RequestBody Maintenance m) {
        return maintenanceService.add(m);
    }
    @Operation(summary = "Modifier Maintenances ", description = "Modifie Maintenances")

    @PutMapping
    public Maintenance update(@RequestBody Maintenance m) {
        return maintenanceService.modify(m);
    }
    @Operation(summary = "Supprimer Maintenances ", description = "retourne Maintenances")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        maintenanceService.remove(id);
    }
}