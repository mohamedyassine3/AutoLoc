package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.EmployeService;

import java.util.List;

@Tag(name = "Employes", description = "Gestion des employés")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final EmployeService employeService;

    @Operation(summary = "Lister les employés", description = "Retourne tous les employés")
    @GetMapping
    public List<Employe> getAll() {
        return employeService.retrieveAll();
    }

    @Operation(summary = "Retourner un employé", description = "Retourne un employé par id")
    @GetMapping("/{id}")
    public Employe getOne(@PathVariable Long id) {
        return employeService.retrieve(id);
    }

    @Operation(summary = "Ajouter un employé")
    @PostMapping
    public Employe create(@RequestBody Employe e) {
        return employeService.add(e);
    }

    @Operation(summary = "Modifier un employé")
    @PutMapping
    public Employe update(@RequestBody Employe e) {
        return employeService.modify(e);
    }

    @Operation(summary = "Supprimer un employé")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeService.remove(id);
    }

    @Operation(summary = "Désaffecter un employé", description = "Retire l'employé de son agence actuelle")
    @PutMapping("/{idEmploye}/desaffecter")
    public Employe desaffecter(@PathVariable Long idEmploye) {
        return employeService.desaffecterEmploye(idEmploye);
    }

    @Operation(summary = "Affecter un employé à une agence",
            description = "L'employé ne doit appartenir à aucune agence (désaffecter d'abord)")
    @PutMapping("/{idEmploye}/affecter/{idAgence}")
    public Employe affecter(@PathVariable Long idEmploye, @PathVariable Long idAgence) {
        return employeService.affecterEmploye(idEmploye, idAgence);
    }
}