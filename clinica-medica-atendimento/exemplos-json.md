# Exemplos de JSON para Teste da API de Atendimento

## 1. Registrar Novo Atendimento

### Request Body
```json
{
  "descricao": "Consulta de rotina - paciente com dor de cabeça",
  "dataHora": "2024-01-15 14:30:00",
  "pacienteId": 1,
  "medicoId": 2
}
```

### Response (201 Created)
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

## 2. Atualizar Atendimento

### Request Body
```json
{
  "descricao": "Consulta de rotina - paciente com dor de cabeça - ATUALIZADO",
  "dataHora": "2024-01-15 15:00:00"
}
```

### Response (200 OK)
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

## 3. Listar Atendimentos

### Response (200 OK)
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
    },
    {
      "id": 2,
      "descricao": "Consulta de emergência - paciente com febre alta",
      "dataHora": "2024-01-15T16:00:00",
      "pacienteId": 3,
      "pacienteNome": "Maria Oliveira",
      "medicoId": 1,
      "medicoNome": "Dr. Carlos Pereira",
      "ativo": true,
      "dataCriacao": "2024-01-15T16:00:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

## 4. Buscar Atendimento por ID

### Response (200 OK)
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

## 5. Listar Atendimentos por Paciente

### Response (200 OK)
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
    },
    {
      "id": 3,
      "descricao": "Retorno - paciente com melhora dos sintomas",
      "dataHora": "2024-01-20T10:00:00",
      "pacienteId": 1,
      "pacienteNome": "João Silva",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-20T10:00:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

## 6. Listar Atendimentos por Médico

### Response (200 OK)
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
    },
    {
      "id": 4,
      "descricao": "Consulta de rotina - paciente com pressão alta",
      "dataHora": "2024-01-16T09:00:00",
      "pacienteId": 5,
      "pacienteNome": "Pedro Costa",
      "medicoId": 2,
      "medicoNome": "Dr. Maria Santos",
      "ativo": true,
      "dataCriacao": "2024-01-16T09:00:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

## 7. Buscar Atendimentos por Período

### Response (200 OK)
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
    },
    {
      "id": 2,
      "descricao": "Consulta de emergência - paciente com febre alta",
      "dataHora": "2024-01-15T16:00:00",
      "pacienteId": 3,
      "pacienteNome": "Maria Oliveira",
      "medicoId": 1,
      "medicoNome": "Dr. Carlos Pereira",
      "ativo": true,
      "dataCriacao": "2024-01-15T16:00:00"
    }
  ],
  "timestamp": "2024-01-15T14:30:00"
}
```

## 8. Erro de Validação

### Response (400 Bad Request)
```json
{
  "success": false,
  "message": "Dados inválidos",
  "data": {
    "descricao": "A descrição deve ter entre 10 e 500 caracteres",
    "pacienteId": "O ID do paciente é obrigatório"
  },
  "timestamp": "2024-01-15T14:30:00"
}
```

## 9. Erro de Recurso Não Encontrado

### Response (400 Bad Request)
```json
{
  "success": false,
  "message": "Atendimento não encontrado com ID: 999",
  "data": null,
  "timestamp": "2024-01-15T14:30:00"
}
```

## 10. Erro Interno do Servidor

### Response (500 Internal Server Error)
```json
{
  "success": false,
  "message": "Erro interno do servidor",
  "data": null,
  "timestamp": "2024-01-15T14:30:00"
}
```

## Exemplos de Dados para Teste

### Pacientes
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "idade": 35,
    "cpf": "123.456.789-00"
  },
  {
    "id": 3,
    "nome": "Maria Oliveira",
    "idade": 28,
    "cpf": "987.654.321-00"
  },
  {
    "id": 5,
    "nome": "Pedro Costa",
    "idade": 45,
    "cpf": "456.789.123-00"
  }
]
```

### Médicos
```json
[
  {
    "id": 1,
    "usuario": "Dr. Carlos Pereira",
    "especialidade": "Clínico Geral"
  },
  {
    "id": 2,
    "usuario": "Dr. Maria Santos",
    "especialidade": "Neurologia"
  }
]
```

## Como Usar no Postman

1. Importe a collection `postman-collection.json`
2. Configure as variáveis de ambiente se necessário
3. Execute os testes na seguinte ordem:
   - Registrar Atendimento
   - Buscar Atendimento por ID
   - Listar Todos os Atendimentos
   - Atualizar Atendimento
   - Cancelar Atendimento

## Como Usar no cURL

### Registrar Atendimento
```bash
curl -X POST http://localhost:8082/atendimento/api/v1/atendimentos \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Consulta de rotina - paciente com dor de cabeça",
    "dataHora": "2024-01-15 14:30:00",
    "pacienteId": 1,
    "medicoId": 2
  }'
```

### Buscar Atendimento por ID
```bash
curl -X GET http://localhost:8082/atendimento/api/v1/atendimentos/1
```

### Listar Todos os Atendimentos
```bash
curl -X GET http://localhost:8082/atendimento/api/v1/atendimentos
```

### Atualizar Atendimento
```bash
curl -X PUT http://localhost:8082/atendimento/api/v1/atendimentos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Consulta de rotina - paciente com dor de cabeça - ATUALIZADO",
    "dataHora": "2024-01-15 15:00:00"
  }'
```

### Cancelar Atendimento
```bash
curl -X DELETE http://localhost:8082/atendimento/api/v1/atendimentos/1
``` 