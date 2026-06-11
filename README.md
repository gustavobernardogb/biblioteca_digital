# Biblioteca Digital

Projeto desenvolvido como Situação de Aprendizagem (SA). A aplicação consiste em um sistema de gerenciamento de biblioteca utilizando Java Web (JSP, Servlets, JPA, Hibernate) para as funções de cadastro de usuários, livros, e o controle prático de empréstimos e devoluções.

## Requisitos do Sistema

- **Java JDK** 17 ou superior
- **Apache Maven** 3.9+
- **MySQL Server** rodando na porta 3306

## Configuração do Banco de Dados

1. Inicie o seu servidor MySQL.
2. Crie o banco de dados chamado `biblioteca_digital`:
   ```sql
   CREATE DATABASE biblioteca_digital;
   ```
3. (Opcional) Você pode importar o arquivo `banco_de_dados.sql` fornecido junto ao projeto para criar as tabelas e dados iniciais, ou deixar que o Hibernate gere as tabelas automaticamente pela propriedade `update` que está configurada no `persistence.xml`.

## Como Instalar e Rodar a Aplicação

1. Baixe ou clone o repositório em sua máquina.
2. Abra um terminal na pasta raiz do projeto (`biblioteca-digital`).
3. Compile o projeto e execute o servidor Jetty com o seguinte comando:
   ```bash
   mvn clean compile jetty:run
   ```
   *(Dependendo do seu ambiente, no Windows, utilize o caminho do seu maven, exemplo: `..\apache-maven-3.9.6\bin\mvn.cmd clean compile jetty:run`)*
4. Após o aviso de `[INFO] Started ServerConnector...`, acesse no seu navegador:
   🔗 **http://localhost:8080/**

## Como Executar os Testes Unitários (JUnit)

O sistema conta com testes unitários que validam regras de negócios (cálculo de multas, datas de devolução e validação de e-mail). Para executá-los, rode no terminal:
```bash
mvn test
```

## Estrutura e Tecnologias

- **Backend**: Java 17, Jakarta EE (Servlets, JSP, JSTL)
- **Persistência**: JPA / Hibernate
- **Banco de Dados**: MySQL
- **Frontend**: HTML5, CSS3
- **Testes**: JUnit 5
