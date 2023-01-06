package sirs.group35.ala.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirs.group35.ala.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, UUID> {

}
