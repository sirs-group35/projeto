package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalCaseRepository extends JpaRepository<LegalCase,Long> {


}
