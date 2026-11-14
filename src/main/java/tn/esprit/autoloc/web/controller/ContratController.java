package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.ContratService;

import java.util.List;
@Tag(name = "Contrats", description = "Gestion des contrats")

@RestController
@RequestMapping("/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final ContratService contratService;
    @Operation(summary = "Lister les contrats ", description = "Retourne tous les contrats")

    @GetMapping
    public List<Contrat> getAll() {
        return contratService.retrieveAll();
    }
    @Operation(summary = "Retourner contrat ", description = "retourne contrat")

    @GetMapping("/{id}")
    public Contrat getOne(@PathVariable Long id) {
        return contratService.retrieve(id);
    }
    @Operation(summary = "Ajouter contrat ", description = "ajoute contrat")

    @PostMapping
    public Contrat create(@RequestBody Contrat c) {
        return contratService.add(c);
    }
    @Operation(summary = "Modifier contrat ", description = "modifie contrat")

    @PutMapping
    public Contrat update(@RequestBody Contrat c) {
        return contratService.modify(c);
    }
        @Operation(summary = "Supprimer contrat ", description = "supprime contrat")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratService.remove(id);
    }
}