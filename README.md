# ALA Project

### Dependências
---

#### WebServer
1 - Instalar jdk 17

    sudo apt-get install openjdk-17-jdk-headless

2 - Instalar Maven

    sudo apt-get install mvn

#### Database
1 - Instalar mysql 10

    sudo apt-get install mysql-server

2 - Configurar mysql
    
    sudo systemctl enable mysql
    sudo systemctl start mysql
    sudo mysql -u root -p

No terminal do mysql fazer:
    
    DROP USER 'root'@'localhost';
    CREATE USER 'ala'@'192.168.0.11' IDENTIFIED BY 'password'; -- (literalmente password)
    CREATE DATABASE `ala`;
    GRANT INSERT, UPDATE, DELETE, SELECT, REFERENCES, ALTER, CREATE ON ala.* TO 'ala'@'192.168.0.11';
    FLUSH PRIVILEGES;

### Correr Backend
---

Compilar: `mvn clean compile`

Correr testes: `mvn test`

Correr: `mvn spring-boot:run`

# Voilá!
