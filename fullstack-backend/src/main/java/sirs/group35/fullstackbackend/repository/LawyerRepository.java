package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawyerRepository extends JpaRepository<Lawyer,Long> {

}
