-- Dados de teste para o micro serviço de atendimento
-- Este arquivo será executado automaticamente na inicialização da aplicação

-- Primeiro, inserir funcionários (médicos) para teste
-- Usando INSERT IGNORE para evitar duplicatas
INSERT IGNORE INTO tb_funcionario (
    id, usuario, senha, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, data_nascimento, especialidade_id, tipo
) VALUES 
    (1, 'Dr. Carlos Pereira', 'admin', 40, 'M', '111.111.111-11', 'Rua dos Médicos', '100', 'Sala 1', 'Centro', 'São Paulo', 'SP', '(11) 11111-1111', 'carlos.pereira@clinica.com', '1983-03-15 00:00:00', NULL, 'MEDICO'),
    (2, 'Dr. Maria Santos', 'admin', 35, 'F', '222.222.222-22', 'Av. das Clínicas', '200', 'Sala 2', 'Vila Mariana', 'São Paulo', 'SP', '(11) 22222-2222', 'maria.santos@clinica.com', '1988-07-20 00:00:00', NULL, 'MEDICO');

-- Depois, inserir pacientes para teste
-- Usando INSERT IGNORE para evitar duplicatas
INSERT IGNORE INTO tb_paciente (
    id, nome, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, data_nascimento
) VALUES 
    (1, 'João Silva', 35, 'M', '123.456.789-00', 'Rua das Flores', '100', 'Apto 101', 'Centro', 'São Paulo', 'SP', '(11) 99999-9999', 'joao.silva@email.com', '1988-05-15 00:00:00'),
    (2, 'Maria Oliveira', 28, 'F', '987.654.321-00', 'Av. Paulista', '500', 'Sala 200', 'Bela Vista', 'São Paulo', 'SP', '(11) 88888-8888', 'maria.oliveira@email.com', '1995-08-20 00:00:00'),
    (3, 'Pedro Costa', 45, 'M', '456.789.123-00', 'Rua Augusta', '300', '', 'Consolação', 'São Paulo', 'SP', '(11) 77777-7777', 'pedro.costa@email.com', '1978-12-10 00:00:00'),
    (4, 'Ana Paula', 32, 'F', '789.123.456-00', 'Rua Oscar Freire', '150', 'Apto 50', 'Jardins', 'São Paulo', 'SP', '(11) 66666-6666', 'ana.paula@email.com', '1991-11-05 00:00:00'),
    (5, 'Roberto Almeida', 50, 'M', '321.654.987-00', 'Rua 7 de Abril', '75', 'Sala 10', 'República', 'São Paulo', 'SP', '(11) 55555-5555', 'roberto.almeida@email.com', '1973-04-12 00:00:00');

-- Por último, inserir alguns atendimentos de exemplo
-- Usando INSERT IGNORE para evitar duplicatas
INSERT IGNORE INTO tb_atendimento (
    id, descricao, data_hora, ativo, paciente_id, medico_id
) VALUES 
    (1, 'Consulta de rotina - paciente com dor de cabeça', '2024-01-15 14:30:00', true, 1, 2),
    (2, 'Consulta de emergência - paciente com febre alta', '2024-01-15 16:00:00', true, 2, 1),
    (3, 'Retorno - paciente com melhora dos sintomas', '2024-01-20 10:00:00', true, 1, 2),
    (4, 'Consulta de rotina - paciente com pressão alta', '2024-01-16 09:00:00', true, 3, 2),
    (5, 'Avaliação inicial - paciente com dor nas costas', '2024-01-17 11:00:00', true, 4, 1); 