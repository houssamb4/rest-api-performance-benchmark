# 🚀 Benchmark de Performances des Web Services REST

## 📊 Étude Comparative des Frameworks d'API REST Java

### Description
Ce projet de recherche analyse les performances de différentes approches d'implémentation d'API REST pour un modèle de domaine e-commerce. L'étude compare les temps de réponse, le débit et la consommation des ressources entre plusieurs frameworks et patterns architecturaux Java.

### 🎯 Objectifs de la Recherche
- **Comparaison des performances** : Mesurer les temps de réponse, le débit et l'utilisation des ressources
- **Évaluation architecturale** : Analyser différentes approches d'implémentation (Spring Data REST, JPA, JDBC)
- **Analyse de scalabilité** : Évaluer les performances sous charge croissante
- **Bonnes pratiques** : Identifier les patterns optimaux pour les APIs REST hautes performances

### 🏗️ Modèle de Domaine
L'étude se concentre sur deux entités principales :
- **`Category`** : Catégories de produits avec codes uniques et noms
- **`Item`** : Produits avec SKU, prix, stock et relations catégorielles

### 🛠️ Stack Technologique
- **Java 17+** (version LTS)
- **Spring Boot 3.x** et **Quarkus**
- **PostgreSQL** / **MySQL**
- **JMeter** pour les tests de charge
- **Docker** pour la cohérence d'environnement
- **Grafana + Prometheus** pour le monitoring

### 🚀 Démarrage Rapide

```bash
# Cloner le dépôt
git clone https://github.com/houssamb4/rest-api-performance-benchmark.git
