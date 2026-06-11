CREATE DATABASE IF NOT EXISTS `biblioteca_digital`;
USE `biblioteca_digital`;

-- Estrutura da Tabela de Usuários
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Inserindo um usuário teste
INSERT INTO `usuario` (`nome`, `email`, `senha`) VALUES 
('Leitor de Teste', 'teste@biblioteca.com', '12345678');

-- Estrutura da Tabela de Livros
CREATE TABLE IF NOT EXISTS `livro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `localizacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Inserindo um livro teste
INSERT INTO `livro` (`nome`, `genero`, `localizacao`) VALUES 
('O Senhor dos Anéis', 'Fantasia', 'Prateleira A1');

-- Estrutura da Tabela do Empréstimo
CREATE TABLE IF NOT EXISTS `emprestimo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dataEmprestimo` date DEFAULT NULL,
  `dataPrevistaDevolucao` date DEFAULT NULL,
  `dataDevolucaoReal` date DEFAULT NULL,
  `multa` double DEFAULT NULL,
  `livro_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`livro_id`) REFERENCES `livro` (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
