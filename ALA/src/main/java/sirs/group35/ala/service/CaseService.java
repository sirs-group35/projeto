package sirs.group35.ala.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.web.dto.LegalCaseDTO;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public interface CaseService {

    List<LegalCaseDTO> listAllLegalCases();

    LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO);

    String submitDocument(UUID caseId, MultipartFile file, Long timestamp, String signedHash) throws IOException;

    List<String> getDocuments(UUID id);

    FileDB getDocument(UUID id, UUID documentId);

    void deleteDocument(UUID id, UUID documentId);
}
