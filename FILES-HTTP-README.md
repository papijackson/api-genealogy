## 📚 FICHIERS DE TEST HTTP - API CUSTOMER

Vous avez 4 fichiers à votre disposition pour tester l'API Customer:

### 📄 Fichiers `.http`

#### 1. **customer-api.http** (3.0 KB)
   - **Description**: Fichier complet avec tous les scénarios de test
   - **Contenu**:
     - ✅ Création de 3 customers différents
     - ✅ Récupération (tous, par ID, par email)
     - ✅ Mise à jour
     - ✅ Suppression
     - ✅ Tests d'erreur 404
   - **Idéal pour**: Débuter et comprendre les flux basiques
   - **Utilisation**: Ouvrez dans JetBrains ou VS Code et cliquez sur "Send Request"

#### 2. **customer-crud.http** (3.7 KB)
   - **Description**: Requêtes organisées par opération CRUD
   - **Contenu**:
     - 📝 CREATE (POST) - Créer avec données complètes/minimales
     - 📖 READ (GET) - Récupérer tous, par ID, par email
     - ✏️ UPDATE (PUT) - Mettre à jour existant et inexistant
     - 🗑️ DELETE (DELETE) - Supprimer
     - 🧪 Scénarios de test complets
   - **Idéal pour**: Structure claire et méthodique
   - **Utilisation**: Suivez les scénarios numérotés

#### 3. **customer-advanced-tests.http** (5.4 KB)
   - **Description**: Tests avancés et cas limites
   - **Contenu**:
     - 🧪 Tests de validation (doublons, champs vides)
     - 📊 Tests avec données complexes (caractères spéciaux, noms longs)
     - 🔍 Tests de recherche avancée
     - ⚡ Tests de performance
     - 🔄 Tests de mise à jour partielle
   - **Idéal pour**: Validation complète et tests d'intégration
   - **Utilisation**: Vérifier les comportements attendus

### 📖 Guide complet

#### **HTTP-TEST-GUIDE.md** (4.6 KB)
   - **Description**: Documentation complète sur comment utiliser les fichiers
   - **Contenu**:
     - 🚀 Instructions pour IntelliJ, VS Code, cURL
     - 📝 Descriptions de toutes les requêtes
     - 🔄 Flux de test recommandé
     - ⚙️ Configuration des variables
     - 📊 Codes HTTP et réponses
     - 💡 Astuces et dépannage

---

## 🚀 DÉMARRAGE RAPIDE

### 1. Démarrer l'application
```bash
./mvnw spring-boot:run
```

### 2. Ouvrir les fichiers .http
- **IntelliJ IDEA**: Les fichiers s'ouvrent automatiquement avec un support natif
- **VS Code**: Installez l'extension "REST Client"

### 3. Exécuter les requêtes
- Cliquez sur le triangle ▶️ avant chaque requête
- Ou utilisez les raccourcis: `Ctrl+Alt+R` (IntelliJ), `Cmd+Alt+R` (Mac)

### 4. Consulter les réponses
- Les réponses apparaissent dans le panneau droit
- Visualisez les headers, le corps et le statut HTTP

---

## 📋 STRUCTURE DES REQUÊTES

Tous les fichiers utilisent les variables suivantes:

```http
@baseUrl = http://localhost:8080
@contentType = application/json
```

Adaptez `@baseUrl` à votre environnement (dev, staging, prod).

---

## 🎯 SCENARIOS RECOMMANDÉS

### Scenario 1 - Test Basique (5 min)
1. Ouvrez **customer-api.http**
2. Exécutez les 3 requêtes POST
3. Exécutez `GET /api/customers`
4. Vérifiez les réponses

### Scenario 2 - CRUD Complet (10 min)
1. Ouvrez **customer-crud.http**
2. Suivez les sections CREATE → READ → UPDATE → DELETE
3. Consultez les réponses pour chaque étape

### Scenario 3 - Validation Complète (15 min)
1. Ouvrez **customer-advanced-tests.http**
2. Testez les cas limites
3. Vérifiez les codes d'erreur (404, 400, etc.)

---

## 🔗 ENDPOINTS DISPONIBLES

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/customers` | Récupérer tous les customers |
| GET | `/api/customers/{id}` | Récupérer un customer par ID |
| GET | `/api/customers/email/{email}` | Récupérer un customer par email |
| POST | `/api/customers` | Créer un nouveau customer |
| PUT | `/api/customers/{id}` | Mettre à jour un customer |
| DELETE | `/api/customers/{id}` | Supprimer un customer |

---

## 💾 EXEMPLE D'UNE REQUÊTE

```http
### Créer un customer
POST {{baseUrl}}/api/customers
Content-Type: {{contentType}}

{
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

---

## 🎓 CONSEILS D'UTILISATION

✅ **À faire**:
- Utilisez les variables `@baseUrl` et `@contentType` pour la maintenabilité
- Testez les cas d'erreur (404, 400)
- Vérifiez les headers de réponse
- Documentez vos requêtes personnalisées

❌ **À éviter**:
- Hardcoder les URLs
- Oublier les headers `Content-Type`
- Ignorer les erreurs de validation
- Utiliser les mêmes emails plusieurs fois (violant l'unicité)

---

## 🐛 DÉPANNAGE

| Problème | Solution |
|----------|----------|
| **Connexion refusée** | Vérifiez que l'application est démarrée sur le port 8080 |
| **404 sur POST** | Assurez-vous que `Content-Type: application/json` est présent |
| **Email déjà existant** | Utilisez un email unique (non présent en base) |
| **500 Internal Error** | Vérifiez les logs de l'application |

---

## 📞 SUPPORT

Pour plus d'informations:
- Consultez **HTTP-TEST-GUIDE.md**
- Vérifiez les logs de l'application
- Testez avec cURL pour isoler les problèmes

---

**Bon testing! 🚀**

