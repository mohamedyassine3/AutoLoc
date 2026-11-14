package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.ClientService;

import java.util.List;

@Tag(name = "Clients", description = "Gestion des Clients")

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @Operation(summary = "Lister les clients ", description = "Retourne tous les clients")

    @GetMapping
    public List<Client> getAll() {
        return clientService.retrieveAll();
    }
    @Operation(summary = "Retourner client ", description = "retourne client")

    @GetMapping("/{id}")
    public Client getOne(@PathVariable Long id) {
        return clientService.retrieve(id);
    }
    @Operation(summary = "Ajouter client ", description = "Ajoute client")

    @PostMapping
    public Client create(@RequestBody Client c) {
        return clientService.add(c);
    }
    @Operation(summary = "Modifier client ", description = "modifie client")

    @PutMapping
    public Client update(@RequestBody Client c) {
        return clientService.modify(c);
    }
    @Operation(summary = "Supprimer client ", description = "supprime client")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.remove(id);
    }
}