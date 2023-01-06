package sirs.group35.ala.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirs.group35.ala.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByEmail(String email);

}
