package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
