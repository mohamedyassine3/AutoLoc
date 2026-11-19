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

Java 17, Maven, Spring Boot 3.3.4 (Web, Data JPA, Validation), MySQL 8, Lombok, springdoc-openapi (Swagger UI).

## Lancer le projet

1. Démarrer MySQL (la base `autoloc_db` est créée automatiquement au premier démarrage).
2. Définir le mot de passe de la base en variable d'environnement, sans le versionner :
   - Windows (PowerShell) : `$env:DB_PASSWORD="votre_mot_de_passe"`
   - Linux / macOS : `export DB_PASSWORD=votre_mot_de_passe`
   - (optionnel) `DB_USERNAME`, par défaut `root`.
3. Lancer `AutolocApiApplication` depuis IntelliJ, ou `./mvnw spring-boot:run`.
4. L'application écoute sur le port **8089** avec le contexte `/autoloc`.

## Documentation de l'API (Swagger)

- Swagger UI : <http://localhost:8089/autoloc/swagger-ui/index.html>
- Spécification OpenAPI : <http://localhost:8089/autoloc/v3/api-docs>

## Architecture en couches

```
tn.esprit.autoloc
├── domain                    entités JPA et énumérations
├── repository                Spring Data JPA (un repository par entité)
├── service                   logique métier (ICrudService<T, ID> + un service par entité)
│   ├── validation            règles de validation (VehiculeValidator)
│   ├── logging               journalisation (JournalisationService)
│   ├── tarification          patron Strategy (politiques de prix)
│   └── isp                   interfaces spécialisées (ISP)
├── factory                   patron Factory (factures)
└── web.controller            contrôleurs REST
```

## API REST (Atelier 3)

CRUD complet pour chaque entité : `GET` (liste), `GET /{id}`, `POST`, `PUT`, `DELETE /{id}`.

| Ressource | Chemin |
|---|---|
| Agences | `/agences` |
| Clients | `/clients` |
| Contrats | `/contrats` |
| Employés | `/employes` |
| Équipements | `/equipements` |
| Maintenances | `/maintenances` |
| Paiements | `/paiements` |
| Réservations |