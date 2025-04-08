# Projeto para desafio dio.me - Java Desafio Lava Jato

## O desafio:

Criar uma aplicação Java, com Spring Boot, que aplique os conhecimentos adquiridos sobre criação de APIs com framework Spring.

## Sobre a Aplicação

A aplicação é uma API para sistema de Lava a Jato. É possível fazer controle de serviços (limpeza de veículos), operadores, veículos, tipos de serviços e seus preços e o andamento de cada serviço.

## Documentação da API

Via swagger em: http://localhost:8080/swagger-ui/index.html. O projeto precisa estar em execução. Vejo como na seção abaixo.

## Rodando o projeto:

Se não tiver o gradle instalado, execute o comando abaixo:

```bash
./gradlew bootRun
```

Caso esteja em ambiente Windows, adicione a extensão `.bat` ao comando:

```bash
./gradlew.bat bootRun
```

Se modificar algo na estrutura do projeto, pode ser necessário limpar e fazer build novamente:

```bash
./gradlew clean && ./gradlew build
```
