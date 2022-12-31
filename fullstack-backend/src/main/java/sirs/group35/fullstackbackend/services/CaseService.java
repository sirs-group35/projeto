package sirs.group35.fullstackbackend.services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sirs.group35.fullstackbackend.dto.LegalCaseDTO;
import sirs.group35.fullstackbackend.exception.StorageException;
import sirs.group35.fullstackbackend.model.LegalCase;
import sirs.group35.fullstackbackend.model.Client;
import sirs.group35.fullstackbackend.model.FileDB;
import sirs.group35.fullstackbackend.model.Lawyer;
import sirs.group35.fullstackbackend.repository.LegalCaseRepository;
import sirs.group35.fullstackbackend.repository.ClientRepository;
import sirs.group35.fullstackbackend.repository.FileDBRepository;
import sirs.group35.fullstackbackend.repository.LawyerRepository;

@Service
public class CaseService {

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public List<LegalCaseDTO> listAllLegalCases() {
        List<LegalCase> cases = legalCaseRepository.findAll();
        List<LegalCaseDTO> legalCaseDTOs = new ArrayList<>();
        for (LegalCase legalCase : cases) {
            LegalCaseDTO legalCaseDTO = new LegalCaseDTO();
            legalCaseDTO.setTitle(legalCase.getTitle());
            legalCaseDTO.setDescription(legalCase.getDescription());
            String client = legalCase.getClient().getUsername();
            legalCaseDTO.setClient(client);
            String lawyer = legalCase.getLawyer().getUsername();
            legalCaseDTO.setLawyer(lawyer);
            legalCaseDTO.setSubmittedFiles(legalCase.getFiles().size());
            legalCaseDTOs.add(legalCaseDTO);
        }
        return legalCaseDTOs;
    }

    public LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO) {
        LegalCase newLegalCase = new LegalCase();
        newLegalCase.setTitle(newLegalCaseDTO.getTitle());
        newLegalCase.setDescription(newLegalCaseDTO.getDescription());
        List<Client> client = clientRepository.findAll();
        for (Client c : client) {
            if (c.getUsername().equals(newLegalCaseDTO.getClient())) {
                newLegalCase.setClient(c);
            }
        }
        List<Lawyer> lawyer = lawyerRepository.findAll();
        for (Lawyer l : lawyer) {
            if (l.getUsername().equals(newLegalCaseDTO.getLawyer())) {
                newLegalCase.setLawyer(l);
            }
        }
        legalCaseRepository.save(newLegalCase);
        return newLegalCaseDTO;
    }

    public List<String> listLegalCaseFileNames(String title) {
        List<LegalCase> cases = legalCaseRepository.findAll();
        List<String> fileNames = new ArrayList<>();
        for (LegalCase legalCase : cases) {
            if (legalCase.getTitle().equals(title)) {
                fileNames = legalCase.getFiles().keySet().stream().toList();
            }
        }
        return fileNames;
    }

    private LegalCase getLegalCase(String title) {
        List<LegalCase> cases = legalCaseRepository.findAll();
        for (LegalCase legalCase : cases) {
            if (legalCase.getTitle().equals(title)) {
                return legalCase;
            }
        }
        return null;
    }

    public String submitDocument(String title, MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			
            LegalCase legalCase = getLegalCase(title);
            if (legalCase == null) {
                throw new StorageException("Case not found.");
            }
            if (legalCase.getFiles().containsKey(file.getOriginalFilename())) {
                throw new StorageException("File already exists.");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileDB fileDB = new FileDB();
            fileDB.setName(fileName);
            fileDB.setType(file.getContentType());
            fileDB.setData(file.getBytes());
            legalCase.addFile(fileName, fileDB);

            fileDBRepository.save(fileDB);
            legalCaseRepository.save(legalCase);
		}
		catch (Exception e) {
			return "Failed to store file. "+ e.getMessage();
		}
        return "File stored successfully.";
    }

    public List<String> getDocuments(String title) {
        LegalCase legalCase = getLegalCase(title);
        if (legalCase == null) {
            return null;
        }
        return legalCase.getFiles().keySet().stream().toList();
    }

    public FileDB getDocument(String title, String documentName) {
        LegalCase legalCase = getLegalCase(title);
        if (legalCase == null) {
            return null;
        }
        return legalCase.getFiles().get(documentName);
    }

}
