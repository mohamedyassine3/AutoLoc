package tn.esprit.autoloc.factory;

public class FactureExcel implements Facture {
    @Override
    public String genererContenu() {
        return "Génération de la facture au format Excel (XLSX)";
    }

    @Override
    public String getType() {
        return "EXCEL";
    }
}