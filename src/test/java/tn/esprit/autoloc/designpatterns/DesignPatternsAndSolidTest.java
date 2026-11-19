package tn.esprit.autoloc.designpatterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.factory.*;
import tn.esprit.autoloc.service.tarification.*;
import tn.esprit.autoloc.service.validation.VehiculeValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DesignPatternsAndSolidTest {

    private Vehicule vehiculeValide() {
        Vehicule v = new Vehicule();
        v.setTarifJournalier(BigDecimal.valueOf(100));
        v.setImmatriculation("123 TUN 4567");
        v.setMarque("Peugeot");
        v.setModele("208");
        return v;
    }

    @Test
    @DisplayName("SRP : VehiculeValidator")
    void validator() {
        VehiculeValidator validator = new VehiculeValidator();
        Vehicule invalide = new Vehicule();
        invalide.setTarifJournalier(BigDecimal.ZERO);
        assertThrows(IllegalArgumentException.class, () -> validator.valider(invalide));
        assertDoesNotThrow(() -> validator.valider(vehiculeValide()));
    }

    @Test
    @DisplayName("Strategy : formules")
    void formules() {
        assertEquals(500.0, new TarificationStandard().calculer(100, 5), 0.001);
        assertEquals(180.0, new TarificationWeekend().calculer(100, 2), 0.001);
        assertEquals(800.0, new TarificationLongueDuree().calculer(100, 10), 0.001);
        assertEquals(850.0, new TarificationEntreprise().calculer(100, 10), 0.001);
    }

    @Test
    @DisplayName("Strategy + OCP : TarificationService")
    void service() {
        Map<String, StrategieTarification> map = new HashMap<>();
        map.put("standard", new TarificationStandard());
        map.put("weekend", new TarificationWeekend());
        map.put("longueDuree", new TarificationLongueDuree());
        map.put("entreprise", new TarificationEntreprise());
        TarificationService service = new TarificationService(map);
        Vehicule v = vehiculeValide();

        assertEquals(300.0, service.calculerTarif(v, 3, "standard"), 0.001);
        assertEquals(270.0, service.calculerTarif(v, 3, "weekend"), 0.001);
        assertEquals(240.0, service.calculerTarif(v, 3, "longueDuree"), 0.001);
        assertEquals(255.0, service.calculerTarif(v, 3, "entreprise"), 0.001);

        Exception e = assertThrows(IllegalArgumentException.class, () -> service.calculerTarif(v, 3, "inconnu"));
        assertTrue(e.getMessage().contains("Type de tarification inconnu"));
        assertThrows(IllegalArgumentException.class, () -> service.calculerTarif(v, 0, "standard"));
    }

    @Test
    @DisplayName("Factory : FactureFactory")
    void factory() {
        FactureFactory f = new FactureFactory();
        assertInstanceOf(FacturePdf.class, f.creerFacture("PDF"));
        assertInstanceOf(FactureExcel.class, f.creerFacture("excel"));
        assertThrows(IllegalArgumentException.class, () -> f.creerFacture("WORD"));
    }
}