package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client,Long> {

    // Find a client by username
    @Query("SELECT username FROM user u WHERE u.username = username")
    Client findByUsername(String username);

}
