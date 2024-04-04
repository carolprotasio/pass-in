# NTW Unite - Rocketseat
 Este projeto foi desenvolvido durante o evento NLW Unite da Rocketseat, onde o desafio era criar o projeto Pass.in. 
Utilizei a linguagem Java para desenvolver esta aplicação, segue alguns detalhes sobre o Projeto:

<h1>Especificações</h1>

# pass.in
<p>O pass.in é uma aplicação de gestão de participantes em eventos presenciais.
A ferramenta permite que o organizador cadastre um evento e abra uma página pública de inscrição.
Os participantes inscritos podem emitir uma credencial para check-in no dia do evento.
O sistema fará um scan da credencial do participante para permitir a entrada no evento.</p>

# Requisitos
## Requisitos funcionais
- [ ] O organizador deve poder cadastrar um novo evento;
- [ ] O organizador deve poder visualizar dados de um evento;
- [ ] O organizador deve poser visualizar a lista de participantes;
- [ ] O participante deve poder se inscrever em um evento;
- [ ] O participante deve poder visualizar seu crachá de inscrição;
- [ ] O participante deve poder realizar check-in no evento;

# Regras de negócio
- [ ] O participante só pode se inscrever em um evento uma única vez;
- [ ] O participante só pode se inscrever em eventos com vagas disponíveis;
- [ ] O participante só pode realizar check-in em um evento uma única vez;

# Requisitos não-funcionais
- [ ] O check-in no evento será realizado através de um QRCode;
- [ ] Especificações da API
- [ ] Banco de dados
- [ ] Nessa aplicação vamos utilizar banco de dados relacional (SQL). Para ambiente de desenvolvimento seguiremos com o SQLite pela facilidade do ambiente.
- [ ] Diagrama ERD
<img src="https://file.notion.so/f/f/08f749ff-d06d-49a8-a488-9846e081b224/8f354dec-0218-43af-a16c-16a86f2d82b0/erd.svg?id=1d4a760d-238b-477a-ac6d-c03e0bd682af&table=block&spaceId=08f749ff-d06d-49a8-a488-9846e081b224&expirationTimestamp=1712340000000&signature=uhCjBVqgYe3pr0gfu4OONy0kRx_cF8-LOX8BmoJw6tM&downloadName=erd.svg" alt="Diagrama ERD" style="width:800px;height:800px;">

# Estrutura do banco (SQL)

```sql 
CREATE TABLE "events" (
    "id" TEXT NOT NULL PRIMARY KEY,
    "title" TEXT NOT NULL,
    "details" TEXT,
    "slug" TEXT NOT NULL,
    "maximum_attendees" INTEGER
);

CREATE TABLE "attendees" (
    "id" TEXT NOT NULL PRIMARY KEY,
    "name" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "event_id" TEXT NOT NULL,
    "created_at" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "attendees_event_id_fkey" FOREIGN KEY ("event_id") REFERENCES "events" ("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE "check_ins" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "created_at" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "attendeeId" TEXT NOT NULL,
    CONSTRAINT "check_ins_attendeeId_fkey" FOREIGN KEY ("attendeeId") REFERENCES "attendees" ("id") ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE UNIQUE INDEX "events_slug_key" ON "events"("slug");
CREATE UNIQUE INDEX "attendees_event_id_email_key" ON "attendees"("event_id", "email");
CREATE UNIQUE INDEX "check_ins_attendeeId_key" ON "check_ins"("attendeeId");
```
# Criando o Projeto

-  Projeto foi criado usando Spring Initializr, utilizando as seguintes dependências:
    - Spring Web
    - Flyway
    - Dev Tools
    - Lombok
    - JPA
- Tecnológias Utilizadas
    - IntelliJ IDEA
    - Maven
    - Java 17
    - Insomnia
    - Notion


       
- [ ] Configurar banco de dados na aplicação
    
    ```xml
    		<dependency>
    			<groupId>org.hsqldb</groupId>
    			<artifactId>hsqldb</artifactId>
    			<version>2.7.1</version>
    		</dependency>
    ```
    
- [ ] Criar migrations para criação das tabelas
- [ ] Criar entidades que irão representar os dados
    - [ ] Event
    - [ ]  Attendee
    - [ ]  Check-in
- [ ] Criar repositories
    - [ ]  Event
    - [ ]  Attendee
    - [ ] Check-in
- [ ]  Criar controllers
    - [ ]  **/events**
    - [ ]  **/attendees**
     
## Criando funcionalidade de organizador

- [ ]  Criar `EventService`
    - [ ] Listagem de eventos
    - [ ]  Criação de eventos
- [ ]  Criar Endpoints
    - [ ] GET /events/{eventId}
    - [ ] POST /events
- [ ] Adicionar tratamento para Exceções
- [ ] Criar `AttendeeService`
   - [ ]  Listagem de Attendees de um evento
- [ ]  Criar Endpoint
    - [ ] GET /events/{eventId}/attendees

## Criando funcionalidade do participante

- [ ] Implementar novas funcionalidades no `AttendeeService`
    - [ ]  Inscrição de participante num evento
        - O participante deve enviar `name` e `email`
    - [ ]  Exibição do crachá
    - O retorno do crachá deverá ser o `name`, `email`, `checkInURL` e **`eventTitle`**
- [ ] Criar Endpoints
    - [ ]  POST /events/{eventId}/attendees
    - [ ]  GET /attendees/{attendeeId}/badge
- [ ]  Criar **`CheckInService`**
    - [ ] Criar método para checkIn
- [ ]  Criar Endpoint
    - [ ]  POST /attendees/{attendeeId}/check-in
- [ ]  Tratamento das exceções
