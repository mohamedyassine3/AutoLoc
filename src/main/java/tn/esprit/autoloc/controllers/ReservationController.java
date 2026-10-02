package tn.esprit.autoloc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationService reservationService;

    @PostMapping
    public Reservation creer(@RequestBody Reservation reservation) {
        return reservationService.creerReservation(reservation);
    }

    @GetMapping("/client/{clientId}")
    public List<Reservation> obtenirParClient(@PathVariable Long clientId) {
        return reservationService.obtenirReservationsParClient(clientId);
    }

    @GetMapping
    public List<Reservation> obtenirToutes() {
        return reservationService.obtenirToutesLesReservations();
    }
}