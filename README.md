# ALA Project

### Configurar Backend
---
1 - Instalar jdk 17

    sudo apt-get install openjdk-17-jdk-headless

2 - Instalar Maven

    sudo apt-get install mvn

3 - Instalar mysql 10

    sudo apt-get install mysql-server

4 - Configurar mysql
    
    sudo service mysql start
    sudo mysql -u root

No terminal do mysql fazer:
    
    DROP USER 'root'@'localhost';
    CREATE USER 'root'@'%' IDENTIFIED BY 'password'; (literalmente password)
    create database `ala`;

### Correr Backend
---

Compilar: `mvn clean compile`

Correr testes: `mvn test`

Correr: `mvn spring-boot:run`

### Frontend
---

O Readme que já existia é bastante self-explanatory, mas no fundo fazem:

`sudo apt-get intall npm` para instalar o npm
`npm i` na pasta do frontend para instalar dependências
`npm start` para correr o frontend

# Voilá!