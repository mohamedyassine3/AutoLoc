package tn.esprit.autoloc.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Vehicule;

public interface VehiculeRepository extends JpaRepository<Vehicule,Integer> {
}
