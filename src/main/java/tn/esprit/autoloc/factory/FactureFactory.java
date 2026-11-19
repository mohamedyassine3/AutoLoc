package tn.esprit.autoloc.factory;

import org.springframework.stereotype.Component;

@Component
public class FactureFactory {

    public Facture creerFacture(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type de facture ne peut pas être null");
        }
        return switch (type.trim().toUpperCase()) {
            case "PDF" -> new FacturePdf();
            case "EXCEL", "XLSX" -> new FactureExcel();
            default -> throw new IllegalArgumentException("Type de facture non supporté : " + type);
        };
    }
}