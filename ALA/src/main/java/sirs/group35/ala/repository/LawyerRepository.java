package sirs.group35.ala.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirs.group35.ala.model.Lawyer;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, UUID> {
    Lawyer findByEmail(String email);

}
