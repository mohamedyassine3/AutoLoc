package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.service.PaiementService;

import java.util.List;
@Tag(name = "Paiements", description = "Gestion des paiements")

@RestController
@RequestMapping("/paiements")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;
    @Operation(summary = "Lister les paiments ", description = "Retourne tous les paiments")

    @GetMapping
    public List<Paiement> getAll() {
        return paiementService.retrieveAll();
    }
    @Operation(summary = "Retourner  paiment ", description = "Retourne paiment par id ")

    @GetMapping("/{id}")
    public Paiement getOne(@PathVariable Long id) {
        return paiementService.retrieve(id);
    }
    @Operation(summary = "Ajouter paiment ", description = "ajoute paiment")

    @PostMapping
    public Paiement create(@RequestBody Paiement p) {
        return paiementService.add(p);
    }
    @Operation(summary = "modifier paiment ", description = "modifie paiment")

    @PutMapping
    public Paiement update(@RequestBody Paiement p) {
        return paiementService.modify(p);
    }
    @Operation(summary = "Supprimer paiment ", description = "supprime paiment")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paiementService.remove(id);
    }
}