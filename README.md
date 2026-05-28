# Livros Backend

API REST para cadastro e consulta de livros, autores, assuntos e relatorios.

## Tecnologias

- Java 17
- Spring Boot 4
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 e Mockito
- OpenAPI/Swagger UI
- Docker

## Executar com Docker

Build da imagem:

```bash
docker build -t livros-backend .
```

Executar o container:

```bash
docker run --rm -p 8080:8080 --name livros-backend livros-backend
```

Com Docker Compose:

```bash
docker compose up --build
```

Para parar:

```bash
docker compose down
```

## Banco de dados

O projeto usa H2 em memoria, configurado em `src/main/resources/application.properties`.

Console H2:

```text
http://localhost:8080/h2-console
```

Dados de acesso:

```text
JDBC URL: jdbc:h2:mem:livrosdb
Usuario: admin
Senha: admin
```

O schema e os dados iniciais ficam em `src/main/resources/schema.sql`.

## Documentacao da API

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints

### Autores

```text
GET    /api/autores
GET    /api/autores/{id}
POST   /api/autores
PUT    /api/autores/{id}
DELETE /api/autores/{id}
```

### Assuntos

```text
GET    /api/assuntos
GET    /api/assuntos/{id}
POST   /api/assuntos
PUT    /api/assuntos/{id}
DELETE /api/assuntos/{id}
```

### Livros

```text
GET    /api/livros
GET    /api/livros/{id}
POST   /api/livros
PUT    /api/livros/{id}
DELETE /api/livros/{id}
```

### Relatorios

```text
GET /api/relatorios/livros-por-autor
```

## Exemplos de payload

Criar autor:

```json
{
  "nome": "Machado de Assis"
}
```

Criar assunto:

```json
{
  "descricao": "Romance"
}
```

Criar livro:

```json
{
  "titulo": "Dom Casmurro",
  "editora": "Editora A",
  "edicao": 1,
  "anoPublicacao": "1899",
  "valor": 39.90,
  "autoresIds": [1],
  "assuntosIds": [1]
}
```


