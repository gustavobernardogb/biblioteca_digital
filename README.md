Biblioteca Digital

Sistema de gerenciamento de biblioteca desenvolvido em Java Web (JSP, Servlets, JPA, Hibernate).
Permite o cadastro de usuarios, livros, e o controle de emprestimos e devolucoes.

Requisitos:
- Java JDK 17
- Apache Maven
- MySQL Server

Configuracao do Banco de Dados:
1. Inicie o servidor MySQL.
2. Crie o banco de dados executando: CREATE DATABASE biblioteca_digital;
3. Importe o arquivo banco_de_dados.sql.

Como rodar a aplicacao:
1. Abra o terminal na pasta do projeto.
2. Execute o comando do maven:
mvn clean compile jetty:run
3. Acesse no navegador o endereco http://localhost:8080/

Como executar os testes:
No terminal, execute o comando:
mvn test
