package sirs.group35.fullstackbackend.repository;

import sirs.group35.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
