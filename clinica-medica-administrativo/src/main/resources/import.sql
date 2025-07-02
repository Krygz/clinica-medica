-- Inserir funcionário (médico) para teste
INSERT IGNORE INTO tb_funcionario (
    id, usuario, senha, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, data_nascimento, especialidade_id, tipo
) VALUES (
    1, 'pedro', 'admin', 30, 'M', '00000000000', 'Rua Teste', '123', '', 'Centro', 'Cidade', 'UF', '999999999', 'admin@clinica.com', NOW(), NULL, 'MEDICO'
);

-- Inserir pacientes para teste da API de atendimento
INSERT IGNORE INTO tb_paciente (
    id, nome, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, data_nascimento
) VALUES 
    (1, 'João Silva', 35, 'M', '123.456.789-00', 'Rua das Flores', '100', 'Apto 101', 'Centro', 'São Paulo', 'SP', '(11) 99999-9999', 'joao.silva@email.com', '1988-05-15 00:00:00'),
    (2, 'Maria Oliveira', 28, 'F', '987.654.321-00', 'Av. Paulista', '500', 'Sala 200', 'Bela Vista', 'São Paulo', 'SP', '(11) 88888-8888', 'maria.oliveira@email.com', '1995-08-20 00:00:00'),
    (3, 'Pedro Costa', 45, 'M', '456.789.123-00', 'Rua Augusta', '300', '', 'Consolação', 'São Paulo', 'SP', '(11) 77777-7777', 'pedro.costa@email.com', '1978-12-10 00:00:00');