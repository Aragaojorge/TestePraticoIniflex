# TESTE PRÁTICO - INIFLEX

## Projeto Java para gerenciamento de funcionários e operações básicas CRUD.

## Tecnologias Utilizadas

Java 21

PostgreSQL

JDBC (Java Database Connectivity)

Maven

## Pré-requisitos

Java JDK 21

PostgreSQL

IDE de sua preferência (IntelliJ, Eclipse, VS Code)

Maven

## Configuração do Banco de Dados

### Crie o banco de dados Iniflex no PostgreSQL:

CREATE DATABASE Iniflex;

### Crie a tabela funcionarios:

CREATE TABLE funcionarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    funcao VARCHAR(50),
    salario NUMERIC(10,2)
);

### Configure as credenciais no arquivo de conexão (DatabaseConnection.java):

String url = "jdbc:postgresql://localhost:5432/Iniflex";
String user = "seu_usuario";
String password = "sua_senha";

## Como Rodar o Projeto

Clone o repositório:

git clone https://github.com/seu-usuario/nome-do-projeto.git
cd nome-do-projeto

## Compile e instale as dependências com Maven:

mvn clean install

## Execute a aplicação:

O projeto foi executado no CIL do Intelij, bastando apenas clicar em RUN.

## Funcionalidades

Cadastro e consulta de funcionários

Registro de salário, função e data de nascimento

Listagem de funcionários de várias formas

## Estrutura do Projeto

src/
├── main/
│   ├── java/
│   │   └── com/INIFLEX/
│   └── resources/
├── test/
│   └── java/

## Contribuição

Fork o repositório

Crie uma branch (git checkout -b feature/nova-funcionalidade)

Commit suas mudanças (git commit -m 'Adiciona nova funcionalidade')

Push para a branch (git push origin feature/nova-funcionalidade)

Abra um Pull Request

## Licença

Licenciado sob a MIT License.
