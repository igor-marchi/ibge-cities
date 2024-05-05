# Projeto Cache de Cidades Brasileiras

Este projeto é uma aplicação Spring Boot que fornece uma API REST para criar e listar cidades brasileiras. As cidades são obtidas de uma API externa e armazenadas em um cache Redis.

## Pré-requisitos

-   Java 21
-   Maven
-   Docker
-   Git
-   Redis

## Clonando o projeto

Para clonar o projeto, execute o seguinte comando no terminal:

```bash
git clone https://github.com/igor-marchi/ibge-cities.git
```

## Subindo o banco de dados (Redis)

Para subir o banco de dados, navegue até a pasta docker no diretório do projeto e execute o seguinte comando:

```bash
cd docker
docker-compose up
```

## Executando o projeto

Para executar o projeto, navegue até o diretório do projeto e execute o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints

-   `GET /brazilian/states`: Lista todos os estados.
-   `GET /brazilian/cities/{{stateCode}}`: Lista todas as cidades de um estado específico.

Para mais detalhes sobre os endpoints, consulte os arquivos `BrazilianController.java`.

## Arquivo request.http

O arquivo `request.http` é um arquivo de teste de API que contém exemplos de solicitações HTTP que podem ser feitas para a API do projeto. Ele é útil para testar rapidamente as funcionalidades da API sem a necessidade de um cliente externo, como o Postman.

Aqui estão as solicitações HTTP presentes no arquivo:

1. **Listar Estados**

    Este é um exemplo de uma solicitação GET para listar todos os estados. A URL da solicitação é `http://localhost:8080/brazilian/states`.

    ```http

    ### List States

    GET http://localhost:8080/brazilian/states
    ```

2. **Listar Cidades por Código de Estado**

    Este é um exemplo de uma solicitação GET para listar todas as cidades de um estado específico. A URL da solicitação é `http://localhost:8080/brazilian/cities/{{stateCode}}`. O `{{stateCode}}` é um placeholder que deve ser substituído pelo código do estado cujas cidades você deseja visualizar.

    ```http

    ### List Cities by State Code

    GET http://localhost:8080/brazilian/cities/{{stateCode}}
    ```

Para executar essas solicitações no Visual Studio Code, você pode instalar a extensão "REST Client" e abrir o arquivo `request.http`. Clique no link "Send Request" que aparece acima de cada solicitação.

## Cron Job

O projeto inclui um cron job que é responsável por atualizar o cache de cidades brasileiras. O cron job é definido na classe `BrazilianCityCacheJob`.

O cron job é agendado para ser executado toda terça-feira às 2 da manhã (horário do servidor). Isso é especificado pela expressão cron `0 0 2 * * TUE` no método `cacheBrazilianCityJob`.

O cron job executa as seguintes ações:

1. Recupera a lista de estados brasileiros.
2. Para cada estado, recupera a lista de cidades.
3. Se a lista de cidades não estiver vazia, armazena as cidades no cache.
4. Após armazenar as cidades de um estado no cache, o cron job pausa por 10 segundos antes de processar o próximo estado. Isso é feito para evitar sobrecarregar o serviço de origem das cidades.

<p align="center">Feito com amor por <a href="https://www.linkedin.com/in/igor-marchi/">Igor Marchi</a> ❤️</p>
