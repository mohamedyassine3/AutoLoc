package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.ReservationService;

import java.util.List;

@Tag(name = "Reservations", description = "Gestion des réservations")

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @Operation(summary = "Lister les réservations", description = "Retourne toutes les réservations")

    @GetMapping
    public List<Reservation> getAll() {
        return reservationService.retrieveAll();
    }
    @Operation(summary = "Retourner une réservation", description = "Retourne une réservation par id")

    @GetMapping("/{id}")
    public Reservation getOne(@PathVariable Long id) {
        return reservationService.retrieve(id);
    }
    @Operation(summary = "Ajouter une réservation", description = "Ajoute une réservation")

    @PostMapping
    public Reservation create(@RequestBody Reservation r) {
        return reservationService.add(r);
    }
    @Operation(summary = "Modifier une réservation", description = "Modifie une réservation")

    @PutMapping
    public Reservation update(@RequestBody Reservation r) {
        return reservationService.modify(r);
    }

    @Operation(summary = "Supprimer une réservation", description = "Supprime une réservation")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reservationService.remove(id);
    }
}