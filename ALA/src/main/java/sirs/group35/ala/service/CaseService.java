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

    List<String> listLegalCaseFileNames(String title);

    String submitDocument(String title, MultipartFile file);

    List<String> getDocuments(String title);

    FileDB getDocument(String title, String documentName);

}
