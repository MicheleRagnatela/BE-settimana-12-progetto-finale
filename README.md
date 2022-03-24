# BE-settimana-12-progetto-finale
Epic Energy Service

Questa è un'applicazione creata per gestire i clienti di un'azienda fornitrice di energia denominata "EPIC ENERGY SERVICES".
Il backend è stato realizzato sfruttando la modularità del framework SpringBoot e l'intero sistema è basato su Web Service REST, la persistenza dei dati viene effettuata con PostgreSQL(utilizzo di SpringJPA)

Sono state create 5 entità:
-cliente
-comune
-provincia
-indirizzo
-fattura
-stato fattura

Ogni  entità presenta una o più relazioni gestite secondo le opportune annotazioni e per ognuna di esse sono stati implementati più servizi REST che includono le principali operazioni CRUD. Inoltre per i clienti e le fatture sono state implementate delle operazioni di ordinamento e filtraggio.

La parte frontend è stata creata con l'utilizzo del template engine Thymeleaf

L'applicazione presenta un sistema di autenticazione e autorizzazione basato su token JWT che permette a diversi utenti di accedere alle funzioni del backend e di registrarsi al sistema. Un utente è caratterizzato dalle seguenti proprietà:
username
email
password
nome
cognome

Gli utenti possono essere di tipo USER, abilitato alle sole operazioni di lettura, oppure ADMIN, abilitato a tutte le operazioni. Un utente può avere più ruoli

Nella root del progetto sono stati importati il backup del file .sql di PostgreSQL e la collection di Postman utile per testare le varie richieste HTTP. Di seguito sono riportati i JSON utili per le richieste di tipo POST :

POST CLIENTE

      {

        "ragioneSociale": "Miss Ribellina",
        "partitaIva": "8907654",
        "email": "string@sting",
        "dataInserimento": "2022-03-15",
        "dataUltimoContatto": "2022-03-15",
        "fatturatoAnnuale": 50000,
        "pec": "string@pec",
        "telefono": "3245676543",
        "emailContatto": "contatto@email",
        "nomeContatto": "Joseph",
        "cognomeContatto": "Josephin",
        "telefonoContatto": "3245645321",
        "tipoCliente": "PA",
        "sedeLegale": {
           "via": "via Federico II di Svevia",
           "civico": 12,
           "cap": 7806,
           "localita": "Salso",
           "comune": {
              "id": 700
           }
        },
        "sedeOperativa": {
         "via": "via Gismundo",
           "civico": 123,
           "cap": 78456,
           "localita": "Palese",
           "comune": {
              "id": 1200
           }
        }
      }

POST INDIRIZZO

    {
      "id": 0,
      "via": "string",
      "civico": 0,
      "cap": 0,
      "localita": "string",
      "comune": { "id":1 }
    }

POST FATTURA

    {

      "anno": 2020,
      "data": "2022-03-14",
      "importo":22200,
      "numero": 1,
      "statoFattura": {"id":1},
      "cliente": {
        "id": 14
      }
    } 

POST PROVINCIA

    {

        "nome": "Barletta",
        "provincia": {
            "id": 11
        }
    }

POST USER

    {
        "userName" : "Gerry42365",
        "email" : "michele_ragnatela@outlook.it",
        "password" : "user"
    }


Per la persistenza dei dati di comuni e province sono stati importati nel progetto due file in formato csv.
L'applicazione presenta anche una documentazione con l'interfaccia Swagger, qui l'URL di riferimento:
http://localhost:8080/swagger-ui.html

Inoltre sono stati effettuati alcuni dei principali test di integrazione con l'utilizzo di MockMvc.

 
