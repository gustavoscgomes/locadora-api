# Locadora API

## Descrição
A API de Locadora permite a gestão de uma plataforma de aluguel de carros com autenticação baseada em JWT, onde usuários podem se registrar, realizar login e acessar recursos conforme suas permissões. Os usuários com permissão de administrador (role ADMIN) podem gerenciar completamente a base de dados (CRUD completo em carros, usuários e pacotes de aluguel). Já os usuários com permissão padrão (role USER) podem criar, visualizar, editar e excluir contratos de aluguel de acordo com suas permissões.

## Índice
- [Propósito](#propósito)
- [Tecnologias](#tecnologias)
- [Funcionalidades](#funcionalidades)
- [Autenticação e Autorização](#autenticação-e-autorização)
- [Instalação e Configuração](#instalação-e-configuração)
- [Endpoints](#endpoints)
- [Exemplos de Uso](#exemplos-de-uso)
- [Documentação OpenAPI](#documentação-openapi)
- [Possíveis Melhorias](#possíveis-melhorias)

## Propósito
A API permite a criação de novos usuários com role USER, onde esses usuários podem realizar operações de criação, edição e exclusão de contratos de aluguel. Usuários com a role ADMIN possuem permissões para realizar qualquer operação (CRUD) nos recursos de carro, pacote de aluguel, usuário e contrato de aluguel.

## Tecnologias
- **Java 17**
- **Spring Boot 3.3.4**
- **JWT (JSON Web Token)** para autenticação e autorização
- **MySQL** como banco de dados relacional
- **OpenAPI (Swagger)** para documentação interativa

## Funcionalidades
- **Autenticação JWT**: Autenticação segura via tokens JWT, com roles (ADMIN, USER).
- **Cadastro e Login**: Cadastro de usuários com senha criptografada e login para gerar tokens JWT.
- **Gestão de Aluguel**: Usuários podem visualizar, criar, editar e excluir contratos de aluguel.
- **Gestão de Carros e Pacotes**: Administradores podem adicionar, editar, listar e excluir carros e pacotes de aluguel.

## Autenticação e Autorização
O sistema de autenticação utiliza JWT (Bearer Token). Ao realizar o login, o usuário recebe um token JWT que deve ser incluído no cabeçalho das requisições para acessar endpoints protegidos. As permissões são determinadas pela role atribuída ao usuário:

- **ADMIN**: Acesso completo a todos os recursos.
- **USER**: Acesso restrito para criação e manipulação de contratos de aluguel próprios.

### Exemplo de Token JWT

```plaintext
Authorization: Bearer [token-jwt]
```

## Instalação e Configuração

### Clone o Repositório:
```bash
git clone <URL_DO_REPOSITORIO>
cd locadora-api
```
### Configuração do Banco de Dados MySQL

Crie uma base de dados locadora. No arquivo `application.properties`, atualize as credenciais do banco:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/locadora?createDatabaseIfNotExist=true
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```
### Configuração do JWT
No application.properties, configure a chave JWT:

```properties
api.security.token.secret=my-secret-key
```
### Execute a Aplicação
```bash
./mvnw spring-boot:run
```
### Acesse o Swagger
Documentação interativa disponível em: http://localhost:8080/swagger-ui/index.html

## Endpoints

#### Autenticação
- **Login:** `/auth/login`  
  **Método:** POST  
  **Descrição:** Realiza a autenticação do usuário e retorna o token JWT.

- **Cadastro de Usuário:** `/auth/register`  
  **Método:** POST  
  **Descrição:** Registra um novo usuário com role USER.

#### Carros
- **Listar todos os carros:** `/carros`  
- **Listar carros disponíveis:** `/carros/disponiveis`  
- **Buscar carro por ID:** `/carros/{id}`  
- **Adicionar um novo carro:** `/carros`  
- **Atualizar informações de um carro:** `/carros/{id}`  
- **Excluir um carro:** `/carros/{id}`  

#### Pacotes de Aluguel
- **Listar pacotes de aluguel:** `/carros/pacotes`  
- **Criar um novo pacote de aluguel:** `/carros/pacotes`  
- **Adicionar pacote de aluguel ao carro:** `/carros/{id}/pacotes`  
- **Excluir um pacote de aluguel:** `/carros/pacotes/{id}`  

#### Contratos de Aluguel
- **Listar contratos de aluguel:** `/alugueis`  
- **Criar um novo contrato de aluguel:** `/alugueis/{modelo}`  

#### Usuários
- **Adicionar cartão de crédito:** `/usuarios/cartao`

## Exemplos de Uso

### Exemplo de Requisição de Login

**Requisição:**

```json
POST /auth/login
{
  "username": "user",
  "password": "senha"
}
```
**Resposta:**

```json
Copiar código
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
