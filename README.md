# FastGo Authentication Service

Questo repository contiene il microservizio di autenticazione e gestione utenti per la piattaforma FastGo. È sviluppato in Java Spring Boot e funge da **Identity Provider** centralizzato. Gestisce la registrazione, il login tramite JWT, la gestione dei ruoli e la sincronizzazione dei dati utente con gli altri microservizi tramite RabbitMQ.

## Struttura del Progetto

.
├── src/main/java/com/fastgo/authentication/fatsgo_authentication/
│   ├── config/             # Configurazione Security, RabbitMQ, Cloudinary
│   ├── restControllers/    # Endpoint API (Login, Registrazione, Admin)
│   ├── service/            # Logica di business e orchestrazione RabbitMQ
│   ├── security/           # Filtri JWT e utility di sicurezza
│   ├── domain/             # Entità MongoDB (User, Role, ecc.)
│   ├── repositories/       # Interfacce MongoRepository
│   └── dto/                # Data Transfer Objects
├── src/main/resources/
│   └── application.properties # Configurazione applicativa
├── docker-compose.yml      # Orchestrazione container
└── build.gradle            # Gestione dipendenze Gradle

## Prerequisiti

* Java JDK 17 o superiore
* MongoDB (locale o container)
* RabbitMQ (per la sincronizzazione eventi)
* Account Cloudinary (per upload immagini profilo)

## Configurazione

Il servizio richiede la configurazione delle seguenti proprietà nel file `src/main/resources/application.properties` o tramite variabili d'ambiente:

1. Database (MongoDB):
   spring.data.mongodb.uri=mongodb://authdb:27017/mydatabase

2. RabbitMQ (Messaging):
   spring.rabbitmq.host=localhost (o IP container)
   spring.rabbitmq.port=5672
   spring.rabbitmq.username=guest
   spring.rabbitmq.password=guest

3. Cloudinary (Immagini):
   Richiede una classe di configurazione con:
   cloud_name, api_key, api_secret

## Compilazione e Avvio

1. Clean e Build:
   ./gradlew clean build

2. Avvio Applicazione:
   ./gradlew bootRun


## Architettura e Flusso di Sincronizzazione

Il servizio utilizza un pattern di **Sincronizzazione Asincrona** per mantenere la coerenza dei dati distribuiti.

Quando un utente (Rider, Shopkeeper o Client) si registra:
1. L'utente viene salvato su MongoDB con stato "Non Sincronizzato".
2. Viene inviato un messaggio RPC su RabbitMQ all'exchange `sync-exchange` con routing key specifica (es. `rider.sync.request`).
3. Il microservizio di destinazione (es. fastgo_rider) riceve il dato, crea il profilo locale e risponde "OK".
4. Se la risposta è positiva, l'utente viene confermato nel DB di Auth. Se fallisce, viene effettuato il rollback (cancellazione).

## API Endpoints

### Autenticazione (/auth)
* POST /auth/login
  Effettua il login e restituisce un token JWT (Bearer Token).
  Payload: { "username": "...", "password": "..." }

### Registrazione (/registration)
* POST /registration/client - Registra un nuovo Cliente.
* POST /registration/rider - Registra un nuovo Rider.
* POST /registration/shopkeeper - Registra un nuovo Venditore.
* POST /registration/upload - Carica immagine profilo su Cloudinary.
* GET /registration/geocode - Proxy verso OpenStreetMap (Nominatim) per ottenere coordinate da indirizzi.

### Amministrazione (/admin)
Richiede Header `Authorization: Bearer <TOKEN_ADMIN>`

* GET /admin/users/rider - Lista tutti i rider.
* GET /admin/users/shopkeeper - Lista tutti i venditori.
* GET /admin/users/{id} - Dettagli specifici utente.
* PATCH /admin/users/status - Modifica lo stato utente (es. attivazione/ban).

## Ruoli Gestiti

* USER (Cliente)
* RIDER
* SHOPKEEPER
* ADMIN