package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

import java.util.List;

@Tag(name = "Employes", description = "Gestion des employes")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;
    @Operation(summary = "Lister les employes ", description = "Retourne tous les employes")

    @GetMapping
    public List<Employe> getAll() {
        return employeService.retrieveAll();
    }
    @Operation(summary = "Retourner employes ", description = "retourne employes")

    @GetMapping("/{id}")
    public Employe getOne(@PathVariable Long id) {
        return employeService.retrieve(id);
    }
    @Operation(summary = "Ajouter employes ", description = "ajoute employes")


    @PostMapping
    public Employe create(@RequestBody Employe e) {
        return employeService.add(e);
    }
    @Operation(summary = "Modifier employes ", description = "modifie employes")

    @PutMapping
    public Employe update(@RequestBody Employe e) {
        return employeService.modify(e);
    }
    @Operation(summary = "Supprimer employes ", description = "supprime employes")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeService.remove(id);
    }
}