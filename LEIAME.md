# Fire Tracker

Fire Tracker é um projeto que tem como objetivo criar uma API que armazena valores obtidos de um sensor de incêndio e um sensor de temperatura em um banco de dados. Esses dados podem ser muito úteis em projetos de detecção de incêndios. Além disso, um console foi criado para interagir com a API, acionando uma sirene em caso de incêndio.

## API

Para construir a API, foram utilizados os frameworks Spring Boot, Spring Data JPA e Spring Web. A linguagem de programação utilizada foi Java.

A API possui controladores para solicitar, enviar e excluir dados. Tanto as solicitações quanto as remoções também podem ser feitas inserindo um período de tempo.

## Dados

O banco de dados PostgreSQL foi utilizado para armazenar os valores. Ele armazena a data/horário de inserção dos dados, o nível de emissão infravermelha, a temperatura e um booleano indicando se uma chama foi detectada ou não.

## Hardware

O hardware utilizado foi um computador como servidor e um Arduino como cliente. O shield Ethernet HR911105A foi utilizado para conectar o Arduino à rede. Os sensores KY-026 e KY-013 foram utilizados para detectar o nível de emissão infravermelha e a temperatura, respectivamente.

## Console

Além da API, um console Python foi desenvolvido para interagir com a API e controlar uma sirene em caso de incêndio.

O console utiliza a biblioteca Requests para fazer solicitações à API e a biblioteca multiprocessing para executar a sirene em um processo separado do processo principal.

## Execução

Para executar o projeto, é necessário ter instalado o Java JDK 17 e o PostgreSQL.

Depois de clonar o repositório, é necessário configurar as credenciais de acesso ao banco de dados e as informações de IP e porta do servidor.

## Melhorias Futuras

Algumas melhorias que podem ser feitas no projeto incluem:

* Implementação de autenticação e autorização na API
* Adição de um sistema de alertas via email ou SMS
* Utilização de um microcontrolador mais robusto para lidar com um maior volume de dados e com mais sensores
* Desenvolvimento de um aplicativo mobile para monitorar os dados e controlar a sirene a partir de um smartphone.