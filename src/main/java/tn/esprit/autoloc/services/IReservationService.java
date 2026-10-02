package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Reservation;
import java.util.List;

public interface IReservationService {
    Reservation creerReservation(Reservation reservation);
    List<Reservation> obtenirReservationsParClient(Long clientId);
    List<Reservation> obtenirToutesLesReservations();
}