package tn.esprit.autoloc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.domain.Maintenance;

public interface MaintenanceRipository extends JpaRepository <Maintenance,Integer> {
}
