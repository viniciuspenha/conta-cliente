# conta-cliente
Esta API tem como objetivo gerenciar a conta e transferencia de clientes.

# Config
### DB
Para inicializar o banco de dados MySql:
```
docker-compose up dbmysql
```

### Build
Buildar o projeto usando o maven:
```
mvn clean install
```

### Run
Após isso execute o comando abaixo para inicializar a aplicaçao na porta 8080:
```
mvn spring-boot:run
```

# Endpoints

## Cliente
- Para criar um novo cliente:
```
Post: localhost:8080/cliente/v1
Body: {
      	"nome" : "Vini"
      }
```
- Para listar todos os clientes:
```
Get: localhost:8080/cliente/v1
```
- Para buscar um cliente pelo numero da conta:
```
Get: localhost:8080/cliente/v1/{numeroConta}
```

## Conta
- Para criar uma nova conta:
```
Post: localhost:8080/conta/v1
Body: {
      	"clienteId" : 1
      }
```
- Para adicionar saldo a uma conta:
```
Post: localhost:8080/conta/v1/saldo
Body: {
      	"contaId" : 1,
        "valor" : 100
      }
```
- Para listar todas as contas:
```
Get: localhost:8080/conta/v1
```

## Transferencia
- Para realizar uma nova transferencia:
```
Post: localhost:8080/transferencia/v1
Body: {
      	"contaOrigem" : 1,
        "numeroContaDestino" : "12345",
        "valor" : 100
      }
```
- Para listar todas as transferencias:
```
Get: localhost:8080/transferencia/v1
```
- Para listar todas as transferencias de uma conta:
```
Get: localhost:8080/transferencia/v1/{contaId}
```