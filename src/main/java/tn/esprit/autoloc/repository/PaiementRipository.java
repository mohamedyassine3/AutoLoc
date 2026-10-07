package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Paiement;

public interface PaiementRipository extends JpaRepository<Paiement,Integer> {
}
