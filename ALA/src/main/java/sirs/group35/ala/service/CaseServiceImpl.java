package sirs.group35.ala.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sirs.group35.ala.model.Client;
import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.LegalCase;
import sirs.group35.ala.model.User;
import sirs.group35.ala.repository.ClientRepository;
import sirs.group35.ala.repository.FileDBRepository;
import sirs.group35.ala.repository.LawyerRepository;
import sirs.group35.ala.repository.LegalCaseRepository;
import sirs.group35.ala.repository.UserRepository;
// TODO: Exceptions - import sirs.group35.ala.exception.StorageException;
import sirs.group35.ala.web.dto.LegalCaseDTO;

@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private LegalCaseRepository legalCaseRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    public List<LegalCaseDTO> listAllLegalCases() {
        List<LegalCase> cases = legalCaseRepository.findAll();
        List<LegalCaseDTO> legalCaseDTOs = new ArrayList<>();
        for (LegalCase legalCase : cases) {
            LegalCaseDTO legalCaseDTO = new LegalCaseDTO();
            legalCaseDTO.setTitle(legalCase.getTitle());
            legalCaseDTO.setDescription(legalCase.getDescription());
            String clientEmail = legalCase.getClient().getEmail();
            legalCaseDTO.setClientEmail(clientEmail);
            String lawyerEmail = legalCase.getLawyer().getEmail();
            legalCaseDTO.setLawyerEmail(lawyerEmail);
            legalCaseDTO.setSubmittedFiles(legalCase.getFiles().size());
            legalCaseDTOs.add(legalCaseDTO);
        }
        return legalCaseDTOs;
    }

    public LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO) {
        LegalCase newLegalCase = new LegalCase();
        newLegalCase.setTitle(newLegalCaseDTO.getTitle());
        newLegalCase.setDescription(newLegalCaseDTO.getDescription());
        Client client = clientRepository.findByEmail(newLegalCaseDTO.getClientEmail());
        newLegalCase.setClient(client);

        Lawyer lawyer = lawyerRepository.findByEmail(newLegalCaseDTO.getLawyerEmail());
        newLegalCase.setLawyer(lawyer);
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
                //TODO: Não devia ser IOException
				throw new IOException("Failed to store empty file.");
			}
			
            LegalCase legalCase = getLegalCase(title);
            if (legalCase == null) {
                //TODO: Não devia ser IOException
                throw new IOException("Case not found.");
            }
            if (legalCase.getFiles().containsKey(file.getOriginalFilename())) {
                //TODO: Não devia ser IOException
                throw new IOException("File already exists.");
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
