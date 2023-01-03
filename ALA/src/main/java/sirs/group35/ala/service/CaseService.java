package sirs.group35.ala.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sirs.group35.ala.web.dto.LegalCaseDTO;
import sirs.group35.ala.model.FileDB;

@Service
public interface CaseService {

    public List<LegalCaseDTO> listAllLegalCases();

    public LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO);

    public List<String> listLegalCaseFileNames(String title);

    public String submitDocument(String title, MultipartFile file);

    public List<String> getDocuments(String title);

    public FileDB getDocument(String title, String documentName);

}
