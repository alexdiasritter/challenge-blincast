================================================================================
BLINCAST - DESAFIO
Sistema de Gerenciamento de Documentos
================================================================================

ESTRUTURA DO PROJETO
--------------------

blincast-challenge/
├── src/
│   └── main/
│       ├── java/com/challenge/blincast/
│       │   ├── BlincastApplication.java
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── exception/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
│           └── application.properties
├── blincast.html
├── pom.xml
└── README.md


================================================================================
PRE-REQUISITOS
================================================================================

- Java 21 ou superior
- Maven (ou use o Maven Wrapper ./mvnw incluso no projeto)
- Navegador moderno (Chrome, Firefox, Edge)


================================================================================
COMO RODAR O BACKEND
================================================================================

1. Na raiz do projeto (onde esta o pom.xml), inicie o servidor:

   Linux/Mac:
   ./mvnw spring-boot:run

   Windows:
   mvnw.cmd spring-boot:run

2. Confirme que esta rodando. O console mostrara algo como:

   Started BlincastApplication in 2.5 seconds

   O servidor estara disponivel em: http://localhost:8080

3. Para parar o servidor, pressione Ctrl + C no terminal.


================================================================================
COMO RODAR O FRONTEND
================================================================================

1. Abra o arquivo blincast.html no navegador:

    - Opcao 1: Clique duplo no arquivo blincast.html na raiz do projeto
    - Opcao 2: Arraste o arquivo para o navegador
    - Opcao 3: Pelo terminal:

      Linux/Mac: xdg-open blincast.html
      Windows:   start blincast.html

2. Use a interface:

    - Digite uma Chave (ex: nome)
    - Digite um Valor (ex: Joao)
    - Clique em uma das acoes:

        * CRIAR      -> cria um novo documento
        * ATUALIZAR  -> altera um documento existente
        * DELETAR    -> remove um documento


================================================================================
API REST
================================================================================

Endpoint: POST http://localhost:8080/document

Formato da Requisicao:

{
"action": "create | update | delete",
"key": "identificador",
"value": "conteudo"
}

OBS: "value" e opcional para a acao "delete"

Respostas:

+------------+-----------------------------------+---------------------------+
| HTTP Status| JSON                              | Significado               |
+------------+-----------------------------------+---------------------------+
| 200        | {"status":"ok","code":200}        | Operacao bem-sucedida     |
| 400        | {"status":"error","code":400}     | Requisicao invalida       |
| 404        | {"status":"error","code":404}     | Chave nao encontrada      |
| 409        | {"status":"error","code":409}     | Chave ja existe           |
| 500        | {"status":"error","code":500}     | Erro interno              |
+------------+-----------------------------------+---------------------------+


================================================================================
TESTANDO COM cURL
================================================================================

1. Criar documento:

curl -X POST http://localhost:8080/document \
-H "Content-Type: application/json" \
-d '{"action":"create","key":"nome","value":"Joao"}'

2. Atualizar documento:

curl -X POST http://localhost:8080/document \
-H "Content-Type: application/json" \
-d '{"action":"update","key":"nome","value":"Maria"}'

3. Deletar documento:

curl -X POST http://localhost:8080/document \
-H "Content-Type: application/json" \
-d '{"action":"delete","key":"nome"}'

4. Testar erro de chave duplicada:

curl -X POST http://localhost:8080/document \
-H "Content-Type: application/json" \
-d '{"action":"create","key":"nome","value":"Outro"}'

Resposta esperada: HTTP 409 - Chave ja existe

5. Testar erro de chave inexistente:

curl -X POST http://localhost:8080/document \
-H "Content-Type: application/json" \
-d '{"action":"update","key":"inexistente","value":"teste"}'

Resposta esperada: HTTP 404 - Chave nao encontrada


================================================================================
CONSOLE H2 (BANCO DE DADOS)
================================================================================

Para visualizar os dados diretamente no banco:

1. Com o servidor rodando, acesse:

   http://localhost:8080/h2-console

2. Preencha os campos:

   JDBC URL:  jdbc:h2:mem:blincastdb
   User Name: sa
   Password:  (deixar vazio)

3. Clique em Connect

4. Execute queries SQL de exemplo:

   SELECT * FROM documents;
   SELECT COUNT(*) FROM documents;
   SELECT * FROM documents WHERE doc_key = 'nome';

IMPORTANTE: O banco e em memoria. Os dados sao perdidos ao reiniciar o servidor.


================================================================================
TECNOLOGIAS UTILIZADAS
================================================================================

+-------------------------+------------------+----------------------------+
| Tecnologia              | Versao           | Finalidade                 |
+-------------------------+------------------+----------------------------+
| Java                    | 21               | Linguagem                  |
| Spring Boot             | 3.5.x            | Framework                  |
| Spring Web              | -                | API REST                   |
| Spring Data JPA         | -                | Persistencia               |
| H2 Database             | 2.x              | Banco em memoria           |
| HikariCP                | -                | Pool de conexoes           |
| Virtual Threads         | Java 21          | Concorrencia escalavel     |
| HTML5 + CSS3 + JS       | -                | Interface web              |
+-------------------------+------------------+----------------------------+


================================================================================
SOLUCAO DE PROBLEMAS
================================================================================

+-----------------------------------+-------------------------------------------+
| Problema                          | Solucao                                   |
+-----------------------------------+-------------------------------------------+
| Servidor nao inicia               | Verifique se a porta 8080 esta livre      |
| Frontend nao conecta ao backend   | Confirme que o servidor esta rodando      |
| CORS bloqueado no navegador       | O servidor ja esta configurado com CORS   |
| JAVA_HOME nao definido            | Instale o JDK 21 e configure a variavel   |
| Erro de tabela nao encontrada     | Reinicie o servidor (banco limpo)         |
+-----------------------------------+-------------------------------------------+

================================================================================
NOTAS IMPORTANTES
================================================================================

- O banco de dados e reiniciado a cada execucao do servidor (modo em memoria)
- O campo "value" e opcional apenas para a acao "delete"
- A interface web e o servidor sao completamente independentes
- Nao e necessario instalar nada para o frontend, apenas abrir o HTML

================================================================================
LICENCA
================================================================================

Projeto desenvolvido para o desafio Blincast.