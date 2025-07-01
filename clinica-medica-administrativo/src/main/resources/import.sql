INSERT INTO tb_funcionario (
    usuario, senha, idade, sexo, cpf, rua, numero, complemento, bairro, cidade, estado, contato, email, data_nascimento, especialidade_id, tipo
) VALUES (
             'admin', 'admin', 30, 'M', '00000000000', 'Rua Teste', '123', '', 'Centro', 'Cidade', 'UF', '999999999', 'admin@clinica.com', NOW(), NULL, 'MEDICO'
         );