package sirs.group35.ala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sirs.group35.ala.model.Client;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.LegalCase;

import java.util.List;

public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    List<LegalCase> findByLawyer(Lawyer lawyer);
    List<LegalCase> findByClient(Client client);
}
