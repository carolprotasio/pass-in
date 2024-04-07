# Pass.in - Gestão de Participantes em Eventos Presenciais
<p>O pass.in é uma aplicação de gestão de participantes em eventos presenciais.
A ferramenta permite que o organizador cadastre eventos, escrevam participantes e facilite o check-in no dia do evento.
Os participantes inscritos podem emitir uma credencial para check-in no dia do evento.
O sistema fará um scan da credencial do participante para permitir a entrada no evento.</p>

# Regras de negócio
 - Os participantes só podem se inscrever uma vez em um evento.
 - A inscrição só é permitida em eventos com vagas disponíveis.
 - O check-in só pode ser feito uma única vez por participante.

# Tecnológias Utilizadas
 - IntelliJ IDEA
 - Maven
 - Backend: Java com Spring Boot
 - Insomnia
 - Notion
 - [Swagger](https://nlw-unite-nodejs.onrender.com/docs/static/index.html)
  

# O Projeto foi criado usando Spring Initializr, utilizando as seguintes dependências:
   - Spring Web
   - Flyway
   - Dev Tools
   - Lombok
   - JPA
# Para Executar o Projeto
 1. Clone este repositório:
   ```markdown
   https://github.com/carolprotasio/pass-in-backend-java.git 📋
```
 2. Instale as dependências necessárias e certifique de ter o Maven instalado no seu sistema
 ```markdown
    mvn install
```
 4. Execute o servidor backend.
 5. Execute o servidor frontend.
 6. Acesse a aplicação no navegador.

_______________________________________________________

 # Requisitos realizados no Pass.in
## Requisitos funcionais
- O organizador deve poder cadastrar um novo evento;
- O organizador deve poder visualizar dados de um evento;
- O organizador deve poser visualizar a lista de participantes;
- O participante deve poder se inscrever em um evento;
- O participante deve poder visualizar seu crachá de inscrição;
- O participante deve poder realizar check-in no evento;


## Requisitos não-funcionais
- O check-in no evento será realizado através de um QRCode;
- Especificações da API
- Banco de dados
- Nessa aplicação foi utilizado banco de dados relacional (SQL). 
- Diagrama ERD
<img src="https://efficient-sloth-d85.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F08f749ff-d06d-49a8-a488-9846e081b224%2F8f354dec-0218-43af-a16c-16a86f2d82b0%2Ferd.svg?table=block&id=1d4a760d-238b-477a-ac6d-c03e0bd682af&spaceId=08f749ff-d06d-49a8-a488-9846e081b224&userId=&cache=v2" alt="Diagrama ERD" style="width:800px;height:800px;">

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
# Créditos
Este projeto foi desenvolvido com base no tutorial oferecido pela Rocketseat durante o evento NJW Unite. Agradecemos à equipe da Rocketseat por fornecer recursos valiosos e conhecimento técnico.
Instrutora responsável pelo tutorial: Fernanda Kipper

    
