package sirs.group35.ala.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.web.dto.LegalCaseDTO;

import java.util.List;

@Service
public interface CaseService {

    List<LegalCaseDTO> listAllLegalCases();

    LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO);

    String submitDocument(Long caseId, MultipartFile file);

    List<String> getDocuments(Long id);

    FileDB getDocument(Long id, String documentName);

}
