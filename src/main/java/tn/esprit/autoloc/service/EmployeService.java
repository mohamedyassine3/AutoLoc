package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeService implements ICrudService<Employe, Long> {

    private final EmployeRepository employeRepository;
    private final AgenceRepository agenceRepository;

    @Override
    public List<Employe> retrieveAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe retrieve(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé introuvable : " + id));
    }

    @Override
    public Employe add(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public Employe modify(Employe e) {
        return employeRepository.save(e);
    }

    @Override
    public void remove(Long id) {
        employeRepository.deleteById(id);
    }

    @Transactional
    public Employe desaffecterEmploye(Long idEmploye) {
        Employe employe = retrieve(idEmploye);
        if (employe.getAgence() == null) {
            throw new IllegalStateException("L'employé " + idEmploye + " n'est affecté à aucune agence");
        }
        employe.setAgence(null);
        return employeRepository.save(employe);
    }

    @Transactional
    public Employe affecterEmploye(Long idEmploye, Long idAgence) {
        Employe employe = retrieve(idEmploye);
        Agence agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new RuntimeException("Agence introuvable : " + idAgence));
        if (employe.getAgence() != null) {
            throw new IllegalStateException("L'employé " + idEmploye
                    + " appartient déjà à une agence : désaffectez-le d'abord");
        }
        employe.setAgence(agence);
        return employeRepository.save(employe);
    }
}