package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.AgenceService;

import java.util.List;
@Tag(name = "Agences", description = "Gestion des agences")
@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
public class AgenceController {

    private final AgenceService agenceService;
    @Operation(summary = "Lister les agences ", description = "Retourne tous les agences")

    @GetMapping
    public List<Agence> getAll() { return agenceService.retrieveAll(); }
    @Operation(summary = "Retourner agence ", description = "retourne agence")

    @GetMapping("/{id}")
    public Agence getOne(@PathVariable Long id) { return agenceService.retrieve(id); }
    @Operation(summary = "AJouter agence ", description = "ajoute agence")

    @PostMapping
    public Agence create(@RequestBody Agence a) { return agenceService.add(a); }
    @Operation(summary = "Modifier agence ", description = "modifie agence")

    @PutMapping
    public Agence update(@RequestBody Agence a) { return agenceService.modify(a); }
    @Operation(summary = "Supprimer agence ", description = "supprime agence")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { agenceService.remove(id); }
}