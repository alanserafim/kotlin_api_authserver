# AuthServer

## API REST utilizando Spring Boot com Kotlin

### Curso:
* Pós-Graduação em Desenvolvimento de aplicativos móveis - PUCPR

### Disciplina:
* Desenvolvimento Backend


## Ferramentas
As seguintes ferramentas foram usadas na construção do projeto:

### 👉 **_Backend_**

- Kotlin
- Spring Boot
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Doc
- Jwt (JSON Web Token)

### 👉 **_Desenvolvimento Geral_**

- Editor:
  - IntelliJ IDEA
- Reuniões:
  - Teams
- Diagramas:
  - Draw.io

## Introdução

Este projeto possui o objetivo principal **implementar uma API para autenticação e gerenciamento de informações sobre filmes e séries**.


### Vídeo explicativo do projeto

    * [Youtube](link) ---> será enviado em 11/05 as 21h

## Análise técnica

### Requisitos Funcionais

* **RF01** - Cadastro de Usuário: O sistema deve permitir que novos usuários se registrem fornecendo nome, e-mail e senha.
* **RF02** - Login de Usuário: O sistema deve autenticar usuários e retornar um token de acesso (ex: JWT).
* **RF03** - Diferenciação de Níveis de Acesso: O sistema deve validar permissões (ex: Admin pode editar/excluir; User pode apenas visualizar).
* **RF04** - Manutenção de Títulos: O sistema deve permitir criar, ler, atualizar e excluir (CRUD) registros de filmes e séries.
* **RF05** - Manutenção de Episódios: O sistema deve permitir o CRUD de episódios vinculados a uma temporada e série. 

### Descrição do ambiente técnico

O sistema é composto por um API http que segue o padrão de arquitetura REST.


### Padrão Arquitetural

Foi utilizado o padrão de arquitetura REST para a construção do projeto.
Para organização código foi utilizado o DDD - Domain Driven Design.


### Boas práticas aplicadas

* Injeção de Dependências
* Logs
* Tratamento de Erros
* Documentação com Spring Doc

### Processo de Desenvolvimento de Software - PDS

O PDS segue a metodologia ágil sendo uma abordagem interativa incremental.


### Links do Projeto
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.5/gradle-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.5/reference/web/servlet.html)
* [Validation](https://docs.spring.io/spring-boot/4.0.5/reference/io/validation.html)
* [SpringDoc OpenAPI](https://springdoc.org/)

>Referencias
- [1] RICHARDSON, Chris. Microservices patterns: with examples in Java. Simon and Schuster, 2018.
- [2] LARMAN, Craig. Utilizando UML e padrões. 2aed., Porto Alegre: Bookman Editora, 2006.

### 👨‍💻 Responsável

<table border="0" align="left">
  <tr>
    <td align="center">
      <img src="https://github.com/alanserafim.png" width="160px" alt="Foto do Alan"/><br>
      <sub>
        <a href="https://github.com/alanserafim"> Alan </a>
      </sub>
    </td>
  </tr>
</table>
