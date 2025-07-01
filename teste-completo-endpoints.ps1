Write-Host "=== TESTE COMPLETO DOS ENDPOINTS - CLÍNICA MÉDICA ADMINISTRATIVA ===" -ForegroundColor Cyan

# 1. LOGIN - Obter token
Write-Host "`n1. FAZENDO LOGIN..." -ForegroundColor Yellow
$loginBody = @{usuario="admin";senha="admin"} | ConvertTo-Json

try {
    $loginResponse = Invoke-RestMethod -Uri "http://localhost:8080/auth/login" -Method Post -Body $loginBody -ContentType "application/json"
    Write-Host "✓ Login OK! Token: $($loginResponse.token)" -ForegroundColor Green
    $token = $loginResponse.token
} catch {
    Write-Host "✗ Login falhou: $($_.Exception.Message)" -ForegroundColor Red
    exit
}

# Headers para endpoints protegidos
$headers = @{
    Authorization = "Bearer $token"
    action = "READ"
}

# 2. ENDPOINTS PÚBLICOS
Write-Host "`n2. TESTANDO ENDPOINTS PÚBLICOS..." -ForegroundColor Yellow

# 2.1 Health endpoints
Write-Host "  2.1. Testando /health..." -ForegroundColor White
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/health" -Method Get
    Write-Host "  ✓ /health OK: $($response.status)" -ForegroundColor Green
} catch {
    Write-Host "  ✗ /health falhou: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "  2.2. Testando /health/ready..." -ForegroundColor White
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/health/ready" -Method Get
    Write-Host "  ✓ /health/ready OK: $($response.status)" -ForegroundColor Green
} catch {
    Write-Host "  ✗ /health/ready falhou: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "  2.3. Testando /health/live..." -ForegroundColor White
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/health/live" -Method Get
    Write-Host "  ✓ /health/live OK: $($response.status)" -ForegroundColor Green
} catch {
    Write-Host "  ✗ /health/live falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 2.2 Actuator
Write-Host "  2.4. Testando /actuator/health..." -ForegroundColor White
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/actuator/health" -Method Get
    Write-Host "  ✓ /actuator/health OK: $($response.status)" -ForegroundColor Green
} catch {
    Write-Host "  ✗ /actuator/health falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 2.3 Swagger
Write-Host "  2.5. Testando /swagger-ui.html..." -ForegroundColor White
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/swagger-ui.html" -Method Get
    Write-Host "  ✓ Swagger UI OK (Status: $($response.StatusCode))" -ForegroundColor Green
} catch {
    Write-Host "  ✗ Swagger UI falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 3. ENDPOINTS PROTEGIDOS
Write-Host "`n3. TESTANDO ENDPOINTS PROTEGIDOS..." -ForegroundColor Yellow

# 3.1 Actions Application
Write-Host "  3.1. Testando /actions-application..." -ForegroundColor White
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/actions-application" -Headers $headers -Method Get
    Write-Host "  ✓ /actions-application OK: $($response.Count) ações encontradas" -ForegroundColor Green
} catch {
    Write-Host "  ✗ /actions-application falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 3.2 Especialidades
Write-Host "`n  3.2. TESTANDO ESPECIALIDADES..." -ForegroundColor White

# Listar especialidades
Write-Host "    3.2.1. Testando GET /especialidades/listar..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/especialidades/listar" -Headers $headers -Method Get
    Write-Host "    ✓ Listar especialidades OK: $($response.Count) especialidades encontradas" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Listar especialidades falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# Criar especialidade
Write-Host "    3.2.2. Testando POST /especialidades..." -ForegroundColor Gray
$especialidadeBody = @{
    nome = "Cardiologia"
    descricao = "Especialidade médica que trata do coração e sistema cardiovascular"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/especialidades" -Headers $headers -Method Post -Body $especialidadeBody -ContentType "application/json"
    Write-Host "    ✓ Criar especialidade OK: ID $($response.id)" -ForegroundColor Green
    $especialidadeId = $response.id
} catch {
    Write-Host "    ✗ Criar especialidade falhou: $($_.Exception.Message)" -ForegroundColor Red
    $especialidadeId = 1
}

# Buscar especialidade por ID
Write-Host "    3.2.3. Testando GET /especialidades/$especialidadeId..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/especialidades/$especialidadeId" -Headers $headers -Method Get
    Write-Host "    ✓ Buscar especialidade OK: $($response.nome)" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Buscar especialidade falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 3.3 Perfis
Write-Host "`n  3.3. TESTANDO PERFIS..." -ForegroundColor White

# Listar perfis
Write-Host "    3.3.1. Testando GET /perfis/listar..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/perfis/listar" -Headers $headers -Method Get
    Write-Host "    ✓ Listar perfis OK: $($response.Count) perfis encontrados" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Listar perfis falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# Criar perfil
Write-Host "    3.3.2. Testando POST /perfis..." -ForegroundColor Gray
$perfilBody = @{
    nome = "Administrador"
    descricao = "Perfil com acesso total ao sistema"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/perfis" -Headers $headers -Method Post -Body $perfilBody -ContentType "application/json"
    Write-Host "    ✓ Criar perfil OK: ID $($response.id)" -ForegroundColor Green
    $perfilId = $response.id
} catch {
    Write-Host "    ✗ Criar perfil falhou: $($_.Exception.Message)" -ForegroundColor Red
    $perfilId = 1
}

# Buscar perfil por ID
Write-Host "    3.3.3. Testando GET /perfis/$perfilId..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/perfis/$perfilId" -Headers $headers -Method Get
    Write-Host "    ✓ Buscar perfil OK: $($response.nome)" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Buscar perfil falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# 3.4 Funcionários
Write-Host "`n  3.4. TESTANDO FUNCIONÁRIOS..." -ForegroundColor White

# Listar funcionários
Write-Host "    3.4.1. Testando GET /funcionarios/listar..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/funcionarios/listar" -Headers $headers -Method Get
    Write-Host "    ✓ Listar funcionários OK: $($response.Count) funcionários encontrados" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Listar funcionários falhou: $($_.Exception.Message)" -ForegroundColor Red
}

# Buscar funcionário por ID (usando ID 1 como exemplo)
Write-Host "    3.4.2. Testando GET /funcionarios/1..." -ForegroundColor Gray
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/funcionarios/1" -Headers $headers -Method Get
    Write-Host "    ✓ Buscar funcionário OK: $($response.usuario)" -ForegroundColor Green
} catch {
    Write-Host "    ✗ Buscar funcionário falhou: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n=== TESTE CONCLUÍDO ===" -ForegroundColor Cyan
Write-Host "Resumo:" -ForegroundColor Yellow
Write-Host "- Login: ✓ Funcionando" -ForegroundColor Green
Write-Host "- Endpoints públicos: Testados" -ForegroundColor White
Write-Host "- Endpoints protegidos: Testados com autenticação" -ForegroundColor White
Write-Host "- CRUD de Especialidades: Testado" -ForegroundColor White
Write-Host "- CRUD de Perfis: Testado" -ForegroundColor White
Write-Host "- CRUD de Funcionários: Testado (parcial)" -ForegroundColor White
Write-Host "- Actions Application: Testado" -ForegroundColor White 