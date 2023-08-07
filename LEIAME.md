# Fire Tracker

O Fire Tracker é um projeto que tem como objetivo criar uma API que armazena valores obtidos de um sensor de incêndio e de um sensor de temperatura em um banco de dados. Esses dados podem ser muito úteis em projetos de detecção de incêndios. Além disso, um console e um aplicativo foram criados para interagir com a API, acionando uma sirene e enviando notificações em caso de incêndio.

## Aplicativo

O Firetracker App é um aplicativo para Android que oferece monitoramento em tempo real de um servidor para detectar indícios de incêndio. O aplicativo é projetado para ler dados da API do servidor, que armazena valores obtidos de sensores de incêndio e temperatura. Se houver indicação de incêndio, o aplicativo exibe notificações e envia alertas SMS para o contato especificado.

## API

A API do Firetracker é construída com base nos frameworks Spring Boot, Spring Data JPA e Spring Web. A API armazena os dados dos sensores de incêndio e temperatura em um banco de dados PostgreSQL. Ela oferece URIs para solicitar, postar e excluir dados.

## Hardware

A configuração de hardware consiste em um computador atuando como servidor e um dispositivo Arduino atuando como cliente. O Arduino está conectado à rede usando um shield Ethernet HR911105A. O sensor KY-026 é usado para detectar o nível de emissão de infravermelho, enquanto o sensor KY-013 mede a temperatura.

## Funcionalidades

O Firetracker App oferece as seguintes funcionalidades:

1. Monitoramento em Tempo Real: O aplicativo busca continuamente dados da API do servidor para detectar quaisquer indícios de incêndio.
2. Notificação: Quando o aplicativo identifica uma possível situação de incêndio, ele exibe uma notificação no dispositivo do usuário para alertá-lo sobre o evento.
3. Alerta por SMS: Se configurado, o aplicativo pode enviar alertas por SMS para um contato pré-definido, informando sobre a possível situação de incêndio.
4. Interação do Usuário: O aplicativo permite que os usuários interajam com a API através de diversos controles e configurações, como definir preferências de alerta, endereço do servidor e número de contato.

## Melhorias Futuras

O Firetracker App pode ser aprimorado ainda mais com a implementação das seguintes funcionalidades:

1. Autenticação de Usuário: Implementar autenticação e autorização do usuário para garantir acesso seguro ao aplicativo e evitar acesso não autorizado a dados sensíveis.
2. Controle Remoto: Permitir que os usuários controlem remotamente a sirene e outros dispositivos conectados pelo aplicativo.
3. Análise de Dados: Adicionar recursos de análise de dados para analisar dados históricos e fornecer informações sobre possíveis padrões de incêndio.