# 🚀 Benchmark de Performances des APIs REST

## 📊 Étude Comparative des Frameworks Java pour Web Services REST

### Description
Ce projet de recherche analyse les performances de différentes approches d'implémentation d'API REST pour un modèle de domaine e-commerce. L'étude compare les temps de réponse, le débit et la consommation de ressources entre différents frameworks et patterns architecturaux Java.

## 🎯 Objectifs de la Recherche

- **Comparaison des Performances** : Mesurer les temps de réponse, le débit et l'utilisation des ressources
- **Évaluation Architecturale** : Analyser différentes approches d'implémentation (Spring Data REST, JPA, Jersey)
- **Analyse de Scalabilité** : Évaluer les performances sous charge croissante
- **Bonnes Pratiques** : Identifier les patterns optimaux pour les APIs REST hautes performances

## 🏗️ Modèle de Domaine

L'étude se concentre sur deux entités principales :
- **`Category`** : Catégories de produits avec codes uniques et noms
- **`Item`** : Produits avec SKU, prix, stock et relations catégorielles

### Schéma de Base de Données
```sql
CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(32) UNIQUE NOT NULL,
    name VARCHAR(128) NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE item (
    id BIGSERIAL PRIMARY KEY,
    sku VARCHAR(64) UNIQUE NOT NULL,
    name VARCHAR(128) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    stock INT NOT NULL,
    category_id BIGINT NOT NULL REFERENCES category(id),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

## 🔬 Variantes d'Implémentation

### Variante A : JAX-RS (Jersey) + JPA/Hibernate

- Implémentation manuelle des endpoints avec Jersey

- JPA pour la couche d'accès aux données

- Contrôle total sur la logique métier

### Variante C : Spring Boot + @RestController + JPA/Hibernate

- Spring MVC avec annotations @RestController

- Repositories Spring Data JPA

- Écosystème Spring Boot standard

### Variante D : Spring Boot + Spring Data REST

- Exposition REST automatisée à partir des repositories

- Format hypermédia HAL

- Codage manuel minimal

## 🛠️ Stack Technique

- Java 17+ (version LTS)

- Spring Boot 3.x

- PostgreSQL 14+

- JMeter pour les tests de charge

## 🚀 Démarrage Rapide

```bash
# Cloner le dépôt
git clone https://github.com/houssamb4/rest-api-performance-benchmark.git
cd benchmark-rest-apis

# lancer une variante
mvn spring-boot:run
```

## 👥 Contributeurs

<a href="https://github.com/houssamb4">Houssam Bouzid</a>
<a href="https://github.com/Oussama846">Oussama Darradi</a>
