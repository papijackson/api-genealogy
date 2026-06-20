# API Customer - Guide de test avec les fichiers .http

## 📖 Description

Ce fichier `customer-api.http` contient une collection complète de requêtes HTTP pour tester l'API REST Customer.

## 🚀 Comment utiliser

### Avec JetBrains IntelliJ IDEA

1. **Ouvrez le fichier** `customer-api.http` dans l'IDE
2. **Cliquez sur le triangle ▶️** à gauche de chaque requête pour l'exécuter
3. **Consultez la réponse** dans le panneau à droite

### Avec VS Code

1. Installez l'extension **"REST Client"** (par Huachao Mao)
2. Ouvrez `customer-api.http`
3. Cliquez sur **"Send Request"** au-dessus de chaque requête

### Avec cURL (ligne de commande)

```bash
# Créer un customer
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jean",
    "lastName": "Dupont",
    "email": "jean.dupont@example.com"
  }'

# Récupérer tous les customers
curl http://localhost:8080/api/customers

# Récupérer un customer par ID
curl http://localhost:8080/api/customers/1

# Mettre à jour un customer
curl -X PUT http://localhost:8080/api/customers/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jean-Claude",
    "lastName": "Dupont",
    "email": "jean-claude.dupont@example.com"
  }'

# Supprimer un customer
curl -X DELETE http://localhost:8080/api/customers/1
```

## 📝 Requêtes disponibles

### 1. **Créer des customers** (POST)
- Crée 3 customers différents avec des données complètes

### 2. **Récupérer tous les customers** (GET)
- Liste tous les customers

### 3. **Récupérer un customer par ID** (GET)
- Récupère un customer spécifique avec son ID

### 4. **Récupérer un customer par email** (GET)
- Récupère un customer en utilisant son adresse email

### 5. **Mettre à jour un customer** (PUT)
- Modifie les informations d'un customer existant

### 6. **Supprimer un customer** (DELETE)
- Supprime un customer par son ID

### 7. **Tests d'erreur**
- Récupération d'un customer inexistant (404)
- Mise à jour d'un customer inexistant (404)
- Suppression d'un customer inexistant (404)

## 🔄 Flux de test recommandé

1. **Démarrer l'application** : `./mvnw spring-boot:run`
2. **Créer les 3 customers** (les 3 premières requêtes POST)
3. **Récupérer tous les customers** (GET /api/customers)
4. **Tester les requêtes par ID et email**
5. **Mettre à jour les customers** (PUT)
6. **Vérifier les modifications**
7. **Supprimer un customer** (DELETE)
8. **Vérifier la suppression** (GET 404)

## ⚙️ Configuration

### Variables
- `@baseUrl` = `http://localhost:8080` - L'URL de base de l'API
- `@contentType` = `application/json` - Le type de contenu

### Modifier les variables
Vous pouvez adapter les variables au début du fichier pour votre environnement:

```http
@baseUrl = http://localhost:8080
@contentType = application/json
```

## 📊 Codes de réponse HTTP

| Code | Sens | Exemple |
|------|------|---------|
| **200** | OK - Requête réussie | GET, PUT |
| **201** | Created - Ressource créée | POST |
| **204** | No Content - Suppression réussie | DELETE |
| **404** | Not Found - Ressource inexistante | GET/PUT/DELETE d'un ID invalide |
| **400** | Bad Request - Requête mal formée | Données invalides |

## 🔍 Exemples de réponses

### ✅ Création réussie (201)
```json
{
  "id": 1,
  "firstName": "Jean",
  "lastName": "Dupont",
  "email": "jean.dupont@example.com",
  "phoneNumber": "0123456789",
  "address": "123 Rue de la Paix",
  "city": "Paris",
  "postalCode": "75001",
  "country": "France"
}
```

### ✅ Récupération réussie (200)
```json
[
  {
    "id": 1,
    "firstName": "Jean",
    "lastName": "Dupont",
    "email": "jean.dupont@example.com",
    ...
  }
]
```

### ❌ Erreur 404
```
HTTP/1.1 404 Not Found
```

## 💡 Astuces

1. **Variables d'environnement** : Vous pouvez créer des fichiers `.http` différents pour différents environnements (dev, staging, prod)

2. **Scripts** : Vous pouvez utiliser JavaScript dans les fichiers `.http` avec JetBrains:
   ```http
   > {%
   client.global.set("customer_id", response.body.id);
   %}
   ```

3. **Authentification** : Si vous ajoutez JWT, utilisez:
   ```http
   Authorization: Bearer <token>
   ```

## 🐛 Dépannage

**L'application n'est pas accessible**
- Vérifiez que l'application est démarrée: `./mvnw spring-boot:run`
- Vérifiez que le port 8080 est disponible

**Erreur 404 sur les requêtes POST**
- Assurez-vous que les headers `Content-Type: application/json` sont présents

**Erreur de validation**
- Vérifiez que les champs obligatoires (`firstName`, `lastName`, `email`) sont remplis
- Vérifiez que l'email est valide et unique

