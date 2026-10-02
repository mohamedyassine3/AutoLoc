package tn.esprit.autoloc.reposteries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}