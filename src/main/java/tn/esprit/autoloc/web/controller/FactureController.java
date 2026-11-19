package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.autoloc.factory.FactureFactory;

@Tag(name = "Factures", description = "Génération de factures (patron Factory)")
@RestController
@RequestMapping("/factures")
@RequiredArgsConstructor
public class FactureController {

    private final FactureFactory factureFactory;

    @Operation(summary = "Générer une facture", description = "Types : PDF ou EXCEL")
    @GetMapping("/generer")
    public String generer(@RequestParam(defaultValue = "PDF") String type) {
        return factureFactory.creerFacture(type).genererContenu();
    }
}