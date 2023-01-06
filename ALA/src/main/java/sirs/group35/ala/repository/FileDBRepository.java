package sirs.group35.ala.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirs.group35.ala.model.FileDB;

import java.util.UUID;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {

}
