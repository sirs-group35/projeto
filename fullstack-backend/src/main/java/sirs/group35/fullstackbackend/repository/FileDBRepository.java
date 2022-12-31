package sirs.group35.fullstackbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sirs.group35.fullstackbackend.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

}
