package tn.esprit.autoloc.factory;

public class FacturePdf implements Facture {
    @Override
    public String genererContenu() {
        return "Génération de la facture au format PDF";
    }

    @Override
    public String getType() {
        return "PDF";
    }
}