# 💼 Chatbot InvestBot - Cadastro de Usuários

Sistema de cadastro e gerenciamento de usuários para o Chatbot **InvestBot**, uma plataforma educativa de investimentos com perfil de risco. Esta aplicação foi desenvolvida em **Java 17** com **Spring Boot 3**, arquitetura orientada a serviços (SOA) e exposta por meio de uma **API RESTful** documentada com Swagger.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (em memória)
- Swagger/OpenAPI
- Maven

## 📆 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/fiap/
│   │   ├── controller/       # Controladores REST
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── entity/           # Entidades JPA
│   │   ├── repository/       # Interfaces JPA (UserRepository)
│   │   ├── service/          # Regras de negócio
│   │   └── ApiApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/templates/... (opcional)
```

---

## 🛠️ Como executar

### Requisitos:

- Java 17+
- Maven 3.8+

### Passos:

```bash
# 1. Clone o repositório
git clone https://github.com/linesiscl/Challenge-SOA.git
cd investbot-cadastro

# 2. Compile e execute
```

A aplicação estará disponível em:\
📍 `http://localhost:8080`

Swagger UI:\
📛 `http://localhost:8080/swagger-ui/index.html`

---

## 📘 Endpoints principais

| Método | Endpoint         | Descrição                     |
| ------ | ---------------- | ----------------------------- |
| GET    | `/usuarios`      | Lista todos os usuários       |
| GET    | `/usuarios/{id}` | Retorna um usuário por ID     |
| POST   | `/usuarios`      | Cria um novo usuário          |
| PUT    | `/usuarios/{id}` | Atualiza um usuário existente |
| DELETE | `/usuarios/{id}` | Remove um usuário             |


---

## 🧪 Testes

A API pode ser testada diretamente pelo Swagger UI ou por ferramentas como Postman, Insomnia ou curl.
