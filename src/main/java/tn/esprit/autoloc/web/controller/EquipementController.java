package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.EquipementService;

import java.util.List;
@Tag(name = "Equipements", description = "Gestion des équipements")

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
public class EquipementController {

    private final EquipementService equipementService;

    @Operation(summary = "Lister les equipments ", description = "Retourne tous les equipments")

    @GetMapping
    public List<Equipement> getAll() {
        return equipementService.retrieveAll();
    }
    @Operation(summary = "Retourner equipemnts ", description = "retourne equipments")

    @GetMapping("/{id}")
    public Equipement getOne(@PathVariable Long id) {
        return equipementService.retrieve(id);
    }
    @Operation(summary = "Ajouter equipments ", description = "ajoute equipments")

    @PostMapping
    public Equipement create(@RequestBody Equipement e) {
        return equipementService.add(e);
    }
    @Operation(summary = "Modifier equipments ", description = "modifie equipments")

    @PutMapping
    public Equipement update(@RequestBody Equipement e) {
        return equipementService.modify(e);
    }
    @Operation(summary = "Supprimer equipments ", description = "Supprime equipments")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipementService.remove(id);
    }
}