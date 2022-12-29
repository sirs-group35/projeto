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

Correr testes: `mvn clean install`

Criar Jar: `mvn clean package`

Correr Jar: `java -jar target/fullstack-backend-0.0.1-SNAPSHOTjar`