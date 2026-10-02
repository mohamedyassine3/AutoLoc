package tn.esprit.autoloc.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repositories.ReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation creerReservation(Reservation reservation) {
        reservation.setStatut("PENDING");
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> obtenirReservationsParClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    @Override
    public List<Reservation> obtenirToutesLesReservations() {
        return reservationRepository.findAll();
    }
}