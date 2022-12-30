package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case,Long> {


}
