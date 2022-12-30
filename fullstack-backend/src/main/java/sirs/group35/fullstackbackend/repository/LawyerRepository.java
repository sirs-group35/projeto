package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LawyerRepository extends JpaRepository<Lawyer,Long> {

    @Query("SELECT username FROM user u WHERE u.username = username")
    Lawyer findByUsername(String username);
}
