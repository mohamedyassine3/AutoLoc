# AutoLoc

Plateforme de gestion de location de véhicules multi-agences (projet « autoloc-api »).

## Objectifs du projet

- Gérer le parc de véhicules des agences (catégorie, tarif journalier, statut).
- Permettre aux clients de consulter les véhicules disponibles et de réserver.
- Suivre les contrats, les paiements et la maintenance des véhicules.

## Acteurs

- **Client** : consulter les véhicules disponibles, effectuer une réservation et consulter ses réservations.
- **Agent d'agence** : gérer les clients, les véhicules et les réservations.
- **Responsable d'agence (Manager)** : superviser l'agence, ses employés et ses activités.
- **Administrateur** : gérer les utilisateurs et administrer le système.

## Stack technique

Java 17, Maven, Spring Boot 3 (Web, Data JPA, Validation), MySQL 8, Lombok.

## Lancer le projet

1. Démarrer MySQL (la base `autoloc_db` est créée automatiquement au premier démarrage).
2. Définir le mot de passe de la base en variable d'environnement, sans le versionner :
   - Windows (PowerShell) : `$env:DB_PASSWORD="votre_mot_de_passe"`
   - Linux / macOS : `export DB_PASSWORD=votre_mot_de_passe`
   - (optionnel) `DB_USERNAME`, par défaut `root`.
3. Lancer `AutolocApiApplication` depuis IntelliJ, ou `./mvnw spring-boot:run`.

## Modèle de données (Atelier 2)

| Association | Type | Côté propriétaire (clé étrangère) |
|---|---|---|
| Agence → Vehicule | OneToMany / ManyToOne | `vehicule.id_agence` |
| Agence → Employe | OneToMany / ManyToOne | `employe.id_agence` |
| Client → Reservation | OneToMany / ManyToOne | `reservation.id_client` |
| Vehicule → Reservation | OneToMany / ManyToOne | `reservation.id_vehicule` |
| Maintenance → Vehicule | ManyToOne (unidirectionnelle) | `maintenance.id_vehicule` |
| Reservation ↔ Contrat | OneToOne | `contrat.id_reservation` (unique) |
| Contrat → Paiement | OneToMany / ManyToOne | `paiement.id_contrat` |
| Vehicule ↔ Equipement | ManyToMany | table `vehicule_equipement` |

Choix : le modèle suit le diagramme de classes du cours. Toutes les `@ManyToOne` sont en `LAZY` ; les collections ne sont pas sérialisées en JSON (`@JsonIgnore`) ; la seule composition (losange noir) est Contrat ◆ Paiement, d'où `cascade = ALL` et `orphanRemoval` uniquement sur `Contrat.paiements`.
