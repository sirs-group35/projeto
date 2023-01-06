package sirs.group35.ala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sirs.group35.ala.model.Client;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.LegalCase;

import java.util.List;
import java.util.UUID;

public interface LegalCaseRepository extends JpaRepository<LegalCase, UUID> {
    List<LegalCase> findByLawyer(Lawyer lawyer);

    List<LegalCase> findByClient(Client client);

    LegalCase findByTitle(String title);
}
