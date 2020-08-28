# osworks-rest-api

Esse projeto foi criado através do curso Rest API para iniciantes da AlgaWorks e ilustra a estrutura de uma Rest API através da utilização do framework Spring e MySQL utilizando o JDK14. Nela foram utilizadas outras dependências como Spring Data JPA, o versionamento de banco de dados FlyWay e a biblioteca ModelMapper. 

## Começando

Para executar o projeto será necessário instalar:

- [OpenJDK: Necessário para rodar o projeto Java (a partir da versão 11)](https://jdk.java.net/)

- [Spring Tool Suite: IDE para desenvolvimento do projeto Spring](https://spring.io/tools)

- [Postman: Necessário para enviar requisições HTTP para a API, para que possamos testá-la](
https://www.getpostman.com/downloads/)

- [MySQL Server: Usado para gerenciar o banco de dados do projeto](https://dev.mysql.com/downloads/mysql/)

- [MySQL Workbench: ferramenta para usar o MySQL Server]( https://dev.mysql.com/downloads/workbench/)


## Desenvolvimento

Para começar com o desenvolvimento basta clonar o projeto do github num diretório de sua preferência:
```
cd "diretório de sua preferência"
git clone https://github.com/guerradaniel/osworks-spring-restapi.git
```


## Features

O projeto conta com a construção de um cliente e um chamado. Nele estão criadas as classes de **Cliente**, **OrdemServico** e **StatusOrdemServico**, para que além de criar a Ordem, possamos ver seu status como **ABERTA**, **FINALIZADA** ou **CANCELADA**.
O projeto permite utilizar métodos *CRUD* para criação, listagem, alteração e remoção de clientes e ordens de serviço. Separei em módulos diferentes: *domain* para acesso aos dados (com.algaworks.osworks.domain), *core* para organizar as classes de negócio (com.algaworks.osworks.core) e *api* onde ficam os serviços web (com.algaworks.osworks.api). Um detalhe importante é que utilizei a biblioteca *Model Mapper* para converter objetos de maneira simples. Seu objetivo é automatizar esse processo seguindo a abordagem “convenção sobre configuração”. Seu objeto está instanciado no pacote com.algaworks.osworks.core.ModelMappingConfig.class.

## Configuração

O Maven, o automatizador de *builds* ficará como responsável como gerenciador de dependências. Para gerar o *build* do projeto será necessário instalá-lo através da opção *Maven install*. Clique com o botão direito na raiz do projeto > *Run As ...* > *Maven Install*. O *Maven* baixará as dependências já inseridas no arquivo *pom.xml*.

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.9.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.algaworks.osworks</groupId>
	<artifactId>osworks-api</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>osworks-api</name>
	<description>API do OSWorks</description>

	<properties>
		<java.version>11</java.version>
	</properties>

	<dependencies>		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>2.3.7</version>
		</dependency>		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

> Caso você adicione outras dependências ao projeto, é interessante usar o comando *maven clean* para limpar o console com dependências anteriores. O comando se encontra no mesmo caminho do *Maven install*.


Através do arquivo *pom.xml*, as dependêcias já inseridas darão poder a aplicação para utilizar os seguintes projetos em *Spring*:

![Edit Spring Boot Starters](/readme/doc-spring-edit.png)  


>Caso tenha algum erro no *build* do projeto, você pode instalá-las manualmente clicando com o botão direito na raiz do projeto > *Spring* > *Edit Starters*.

Para configurar o seu banco de dados altere as dependências no arquivo *application.properties* dentro da pasta *resources* (src/main/resources) de acordo com as informações do seu banco de dados. Lembre-se que, caso utilize um banco de dados difernte do *MySQL* você precisará inserir a dependência *Maven* no arquivo *pom.xml*.

```
spring.datasource.url=jdbc:mysql://localhost/osworks?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=seuusuario
spring.datasource.password=suasenha

spring.jpa.properties.hibernate.format_sql=true
spring.jpa.show-sql=true
```


Na pasta *db* (src/main/resources/db/migration) já estão inseridas a criação das tabelas através dos arquivos *V001__cria-tabela-cliente.sql*, *V002__renomeia-coluna-telefone.sql*, *V003__cria-tabela-ordem-servico.sql* e *V004__cria-tabela-comentario.sql*. 
```
create table cliente (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    telefone varchar(20) not null,
    
    primary key(id)
);
```

>Lembre-se que dependendo do banco de dados que você usar os comandos mudarão (como *PostgreSQL*, por exemplo). Altere os arquivos de acordo com a sintaxe. 

O *pom.xml* possui uma um dependência chamada **Spring DevTools** que é usada para dar um restart automático no servidor sempre que uma modificação é feita. Para evitar erros na leitura dos bancos de dados nos arquivos *.sql*, sugiro criar ou alterar as tabelas dentro do seu gerenciador de banco de dados. Ao ver que a configuração da tabela funcionou corretamente, crie o arquivo na pasta especificada sem *versiontype* na frente. 

>Exemplo.: **cria-tabela-cliente.sql**. 
>
>Cole o comando no arquivo, salve e renomeie-o inserindo o *versiontype*.
>
>Exemplo: **V001__cria-tabela-cliente.sql**.
>
>***Ao criar o arquivo, utilize dois undercores "__".**

## Testes

Para realizar os testes na API, abra o Postman e crie as pastas e seus respectivos verbos:

![Organização de pastas e requisições do PostMan](/readme/doc-postman1.PNG)  


### Classe Cliente (ClienteController)

**GET** | Clientes - Listar
```
GET /clientes HTTP/1.1
Host: localhost:8080
```

**GET** | Clientes - Buscar
```
GET /clientes/15 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

**POST** | Clientes - Adicionar
```
POST /clientes HTTP/1.1
Host: localhost:8080
Accept: application/json
Content-Type: application/json

{
    "nome": "João Moura",
    "email": "moura23@algaworks.com.br",
    "telefone": "34 97774-7474"
}
```

**PUT** | Clientes  - Atualizar
```
PUT /clientes/1 HTTP/1.1
Host: localhost:8080
Accept: application/json
Content-Type: application/json

{
    "nome": "João Santos",
    "email": "santos@algaworks.com.br",
    "telefone": "34 5554-5252"
}
```

**DEL** | Clientes - Remover
```
DELETE /clientes/1 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

### Classe Ordem de Serviço (OrdemServicoController)

**GET** | Ordens de Serviço - Listar
```
GET /ordens-servico HTTP/1.1
Host: localhost:8080
```

**GET** | Ordens de Serviço - Buscar 
```
GET /ordens-servico/1 HTTP/1.1
Host: localhost:8080
Accept: application/json
```

**POST** | Ordens de Serviço - Adicionar
```
POST /ordens-servico HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "cliente": {
        "id": 2
    },
    "descricao": "Reparo impressora: sem tinta.",
    "preco": 300.50
}
```

**PUT** | Ordens de Serviço - Finalizar
```
PUT /ordens-servico/2/finalizacao HTTP/1.1
Host: localhost:8080
```

**GET** | Comentario - Listar
```
GET /ordens-servico/1/comentarios HTTP/1.1
Host: localhost:8080
```

**POST** | Comentario - Adicionar
```
POST /ordens-servico/2/comentarios HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "descricao": "Placa mãe foi reparada."
}
```
Para mais informações acesse a [documentação oficial](https://www.postman.com/api-documentation-tool/) do Postman.




## Contribuições

Contribuições são sempre bem-vindas! Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação.

## Links

Os links abaixo possuem informações ricas para a organização de projetos *Spring*.

[Spring Boot um exemplo completo | iMasters](https://imasters.com.br/desenvolvimento/spring-boot-um-exemplo-completo)

[Spring Boot - Developer Tools | by Anderson Magalhães | Medium](https://medium.com/@oandersonbm/spring-boot-developer-tools-bbac76a4742f)

[Model Mapper - Convertendo objetos de maneira simples | Matera](
http://www.matera.com/blog/post/modelmapper-convertendo-objetos-de-maneira-simples)

## Licença

Não se aplica.