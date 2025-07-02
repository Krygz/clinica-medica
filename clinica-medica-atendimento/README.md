# Micro Serviço de Atendimento - Clínica Médica

## Descrição

Este micro serviço é responsável pelo gerenciamento de atendimentos médicos na clínica. Ele fornece uma API REST completa para criar, consultar, atualizar e cancelar atendimentos.

## Tecnologias Utilizadas

- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL 8.0**
- **OpenAPI/Swagger**
- **Spring HATEOAS**
- **Spring Actuator**
- **Lombok**
- **ModelMapper**

## Configuração

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0
- Docker (opcional)

### Configuração do Banco de Dados

1. Certifique-se de que o MySQL está rodando na porta 3333
2. Crie o banco de dados `clinica_medica`
3. Configure as credenciais no `application.properties`

### Executando a Aplicação

```bash
# Compilar o projeto
mvn clean compile

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8082/atendimento`

## Endpoints da API

### Base URL
```
http://localhost:8082/atendimento/api/v1/atendimentos
```

### 1. Registrar Novo Atendimento

**POST** `/api/v1/atendimentos`

**Descrição:** Cria um novo registro de atendimento médico

**Headers:**
```
Content-Type: application/json
```

**Body:**
```json
{
  "descricao": "Consulta de rotina - paciente com dor de cabeça",
  "dataHora": "2024-01-15 14:30:00",
  "pacienteId": 1,
  "medicoId": 2
}
```

**Resposta (201 Created):**
```json
{
  "success": true,
  "message": "Atendimento registrado com sucesso",
  "data": {
    "id": 1,
    "descricao": "Consulta de rotina - paciente com dor de cabeça",
    "dataHora": "2024-01-15T14:30:00",
    "pacienteId": 1,
    "pacienteNome": "João Silva",
    "medicoId": 2,
    "medicoNome": "Dr. Maria Santos",
    "ativo": true,
    "dataCriacao": "2024-01-15T14:30:00",
    "_links": {
      "self": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos/1"
      },
      "atendimentos-paciente": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos/paciente/1"
      },
      "atendimentos-medico": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos/medico/2"
      }
    }
  },
  "timestamp": "2024-01-15T14:30:00"
}
```

### 2. Buscar Atendimento por ID

**GET** `/api/v1/atendimentos/{id}`

**Descrição:** Retorna um atendimento específico pelo seu ID

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimento encontrado",
  "data": {
    "id": 1,
    "descricao": "Consulta de rotina - paciente com dor de cabeça",
    "dataHora": "2024-01-15T14:30:00",
    "pacienteId": 1,
    "pacienteNome": "João Silva",
    "medicoId": 2,
    "medicoNome": "Dr. Maria Santos",
    "ativo": true,
    "dataCriacao": "2024-01-15T14:30:00",
    "_links": {
      "self": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos/1"
      },
      "todos-atendimentos": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos"
      }
    }
  },
  "timestamp": "2024-01-15T14:30:00"
}
```

### 3. Listar Todos os Atendimentos

**GET** `/api/v1/atendimentos`

**Descrição:** Retorna todos os atendimentos registrados

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimentos listados com sucesso",
  "data": [
    {
      "id": 1,
      "descricao": "Consulta de rotina - paciente com dor de cabeça",
      "dataHora": "2024-01-15T14:30:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-15T14:30:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

### 4. Listar Atendimentos Ativos

**GET** `/api/v1/atendimentos/ativos`

**Descrição:** Retorna apenas os atendimentos ativos

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimentos ativos listados com sucesso",
  "data": [
    {
      "id": 1,
      "descricao": "Consulta de rotina - paciente com dor de cabeça",
      "dataHora": "2024-01-15T14:30:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-15T14:30:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

### 5. Listar Atendimentos por Paciente

**GET** `/api/v1/atendimentos/paciente/{pacienteId}`

**Descrição:** Retorna todos os atendimentos de um paciente específico

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimentos do paciente listados com sucesso",
  "data": [
    {
      "id": 1,
      "descricao": "Consulta de rotina - paciente com dor de cabeça",
      "dataHora": "2024-01-15T14:30:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-15T14:30:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

### 6. Listar Atendimentos por Médico

**GET** `/api/v1/atendimentos/medico/{medicoId}`

**Descrição:** Retorna todos os atendimentos de um médico específico

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimentos do médico listados com sucesso",
  "data": [
    {
      "id": 1,
      "descricao": "Consulta de rotina - paciente com dor de cabeça",
      "dataHora": "2024-01-15T14:30:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-15T14:30:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

### 7. Buscar Atendimentos por Período

**GET** `/api/v1/atendimentos/periodo?inicio=2024-01-01 00:00:00&fim=2024-01-31 23:59:59`

**Descrição:** Retorna atendimentos em um período específico

**Parâmetros:**
- `inicio`: Data e hora de início (formato: yyyy-MM-dd HH:mm:ss)
- `fim`: Data e hora de fim (formato: yyyy-MM-dd HH:mm:ss)

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimentos do período listados com sucesso",
  "data": [
    {
      "id": 1,
      "descricao": "Consulta de rotina - paciente com dor de cabeça",
      "dataHora": "2024-01-15T14:30:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-15T14:30:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

### 8. Atualizar Atendimento

**PUT** `/api/v1/atendimentos/{id}`

**Descrição:** Atualiza os dados de um atendimento existente

**Headers:**
```
Content-Type: application/json
```

**Body:**
```json
{
  "descricao": "Consulta de rotina - paciente com dor de cabeça - ATUALIZADO",
  "dataHora": "2024-01-15 15:00:00"
}
```

**Resposta (200 OK):**
```json
{
  "success": true,
  "message": "Atendimento atualizado com sucesso",
  "data": {
    "id": 1,
    "descricao": "Consulta de rotina - paciente com dor de cabeça - ATUALIZADO",
    "dataHora": "2024-01-15T15:00:00",
    "pacienteId": 1,
    "pacienteNome": "João Silva",
    "medicoId": 2,
    "medicoNome": "Dr. Maria Santos",
    "ativo": true,
    "dataCriacao": "2024-01-15T14:30:00",
    "_links": {
      "self": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos/1"
      },
      "todos-atendimentos": {
        "href": "http://localhost:8082/atendimento/api/v1/atendimentos"
      }
    }
  },
  "timestamp": "2024-01-15T14:30:00"
}
```

### 9. Cancelar Atendimento

**DELETE** `/api/v1/atendimentos/{id}`

**Descrição:** Cancela um atendimento (soft delete)

**Resposta (204 No Content):**
```
Sem conteúdo
```

## Códigos de Status HTTP

- **200 OK**: Requisição processada com sucesso
- **201 Created**: Recurso criado com sucesso
- **204 No Content**: Requisição processada com sucesso, sem conteúdo
- **400 Bad Request**: Dados inválidos na requisição
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro interno do servidor

## Validações

### AtendimentoRequestDTO
- `descricao`: Obrigatório, entre 10 e 500 caracteres
- `pacienteId`: Obrigatório
- `medicoId`: Obrigatório
- `dataHora`: Opcional (usa data/hora atual se não informado)

### AtendimentoUpdateDTO
- `descricao`: Obrigatório, entre 10 e 500 caracteres
- `dataHora`: Opcional

## Documentação Swagger

A documentação interativa da API está disponível em:
```
http://localhost:8082/atendimento/swagger-ui.html
```

## Monitoramento

### Actuator Endpoints

- **Health Check**: `http://localhost:8082/atendimento/actuator/health`
- **Info**: `http://localhost:8082/atendimento/actuator/info`
- **Metrics**: `http://localhost:8082/atendimento/actuator/metrics`
- **Prometheus**: `http://localhost:8082/atendimento/actuator/prometheus`

## Estrutura do Projeto

```
clinica-medica-atendimento/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── clinica/
│       │           ├── ClinicaMedicaAtendimentoApp.java
│       │           ├── config/
│       │           │   ├── OpenApiConfig.java
│       │           │   ├── ModelMapperConfig.java
│       │           │   └── ActuatorConfig.java
│       │           ├── controllers/
│       │           │   └── AtendimentoController.java
│       │           ├── dto/
│       │           │   └── ApiResponse.java
│       │           └── exception/
│       │               └── GlobalExceptionHandler.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── README.md
```

## Testes com Postman

### Collection JSON

```json
{
  "info": {
    "name": "Clínica Médica - Atendimento API",
    "description": "Collection para testar a API de atendimentos médicos",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Registrar Atendimento",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"descricao\": \"Consulta de rotina - paciente com dor de cabeça\",\n  \"dataHora\": \"2024-01-15 14:30:00\",\n  \"pacienteId\": 1,\n  \"medicoId\": 2\n}"
        },
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos"]
        }
      }
    },
    {
      "name": "Buscar Atendimento por ID",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "1"]
        }
      }
    },
    {
      "name": "Listar Todos os Atendimentos",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos"]
        }
      }
    },
    {
      "name": "Listar Atendimentos Ativos",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/ativos",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "ativos"]
        }
      }
    },
    {
      "name": "Listar Atendimentos por Paciente",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/paciente/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "paciente", "1"]
        }
      }
    },
    {
      "name": "Listar Atendimentos por Médico",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/medico/2",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "medico", "2"]
        }
      }
    },
    {
      "name": "Buscar Atendimentos por Período",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/periodo?inicio=2024-01-01 00:00:00&fim=2024-01-31 23:59:59",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "periodo"],
          "query": [
            {
              "key": "inicio",
              "value": "2024-01-01 00:00:00"
            },
            {
              "key": "fim",
              "value": "2024-01-31 23:59:59"
            }
          ]
        }
      }
    },
    {
      "name": "Atualizar Atendimento",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"descricao\": \"Consulta de rotina - paciente com dor de cabeça - ATUALIZADO\",\n  \"dataHora\": \"2024-01-15 15:00:00\"\n}"
        },
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "1"]
        }
      }
    },
    {
      "name": "Cancelar Atendimento",
      "request": {
        "method": "DELETE",
        "url": {
          "raw": "http://localhost:8082/atendimento/api/v1/atendimentos/1",
          "protocol": "http",
          "host": ["localhost"],
          "port": "8082",
          "path": ["atendimento", "api", "v1", "atendimentos", "1"]
        }
      }
    }
  ]
}
```

## Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes. 