After running `setup_offline.sh`, execute the following commands in the database (done through `sudo mysql -u root`):

```
CREATE USER 'ala'@'192.168.0.11' IDENTIFIED BY '^S@XW3r7NNpgqXPL';
CREATE DATABASE `ala`;
GRANT INSERT, UPDATE, DELETE, SELECT, REFERENCES, ALTER, CREATE ON ala.* TO 'ala'@'192.168.0.11';
FLUSH PRIVILEGES;
```

Managers need to be created manually.
After running the server at least once (to setup de database), run the following commands, replacing `{email}` with the email from the user you wanna turn into a manager:

```
INSERT INTO role VALUES (2, 'ROLE_LAWYER'), (3, 'ROLE_MANAGER');
UPDATE user_role SET role_id = 3 WHERE email = '{email}';
```
