package tn.esprit.autoloc.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Partie B — SRP (Single Responsibility Principle)
 *
 * Cette classe a une seule responsabilité : centraliser la journalisation
 * (logging) de l'application. Si la stratégie de logs change (ex. : on veut
 * envoyer les logs vers un système distant), seule cette classe est modifiée.
 */
@Service
public class JournalisationService {

    private static final Logger log = LoggerFactory.getLogger(JournalisationService.class);

    public void info(String message) {
        log.info(message);
    }

    public void info(String message, Object... args) {
        log.info(message, args);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void erreur(String message, Throwable cause) {
        log.error(message, cause);
    }
}