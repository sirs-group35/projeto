package sirs.group35.ala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sirs.group35.ala.model.Client;
import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.model.Lawyer;
import sirs.group35.ala.model.LegalCase;
import sirs.group35.ala.repository.ClientRepository;
import sirs.group35.ala.repository.FileDBRepository;
import sirs.group35.ala.repository.LawyerRepository;
import sirs.group35.ala.repository.LegalCaseRepository;
import sirs.group35.ala.web.dto.LegalCaseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
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
            legalCaseDTOs.add(legalCaseDTO);
        }
        return legalCaseDTOs;
    }

    @Override
    public LegalCaseDTO registerLegalCase(LegalCaseDTO newLegalCaseDTO) {
        LegalCase newLegalCase = new LegalCase();

        // print LegalCaseDTO title, description, client email and lawyer email
        System.out.println("LegalCaseDTO: ");
        System.out.println("Title: " + newLegalCaseDTO.getTitle());
        System.out.println("Description: " + newLegalCaseDTO.getDescription());
        System.out.println("Client Email: " + newLegalCaseDTO.getClientEmail());
        System.out.println("Lawyer Email: " + newLegalCaseDTO.getLawyerEmail());

        newLegalCase.setTitle(newLegalCaseDTO.getTitle());

        newLegalCase.setDescription(newLegalCaseDTO.getDescription());

        Client client = clientRepository.findByEmail(newLegalCaseDTO.getClientEmail());

        Lawyer lawyer = lawyerRepository.findByEmail(newLegalCaseDTO.getLawyerEmail());

        // Print Lawyer and Client
        System.out.println("Client: " + client);
        System.out.println("Lawyer: " + lawyer);

        client.addCase(newLegalCase);
        lawyer.addCase(newLegalCase);
        newLegalCase.setLawyer(lawyer);
        newLegalCase.setClient(client);
        legalCaseRepository.save(newLegalCase);

        //clientRepository.save(client);
        //lawyerRepository.save(lawyer);

        return newLegalCaseDTO;

    }

    @Override
    public String submitDocument(Long caseId, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                //TODO: Não devia ser IOException
                throw new IOException("Failed to store empty file.");
            }

            // Print case id
            System.out.println("Case ID: " + caseId);

            // Print file name
            System.out.println("File name: " + file.getOriginalFilename());

            LegalCase legalCase = legalCaseRepository.findById(caseId).get();

            // Print legalCase name
            System.out.println("LegalCase name: " + legalCase.getTitle());

            if (legalCase == null) {
                //TODO: Não devia ser IOException
                throw new IOException("Case not found.");
            }

            if (legalCase.getFiles().stream().map(FileDB::getName).toList().contains(file.getOriginalFilename())) {
                //TODO: Não devia ser IOException
                throw new IOException("File already exists.");
            }
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            FileDB fileDB = new FileDB();
            fileDB.setName(fileName);
            fileDB.setType(file.getContentType());
            fileDB.setData(file.getBytes());
            legalCase.addFile(fileDB);

            fileDBRepository.save(fileDB);
            legalCaseRepository.save(legalCase);
        } catch (Exception e) {
            return "Failed to store file. " + e.getMessage();
        }
        return "File stored successfully.";
    }

    @Override
    public List<String> getDocuments(Long caseId) {
        LegalCase legalCase = legalCaseRepository.findById(caseId).get();
        if (legalCase == null) {
            return null;
        }
        return legalCase.getFiles().stream().map(FileDB::getName).toList();
    }

    @Override
    public FileDB getDocument(Long caseId, Long documentId) {
        LegalCase legalCase = legalCaseRepository.findById(caseId).get();

        if (legalCase == null) {
            return null;
        }

        //Get file with documentId from case
        return legalCase.getFiles().stream().filter(file -> file.getId() == documentId).findFirst().orElse(null);
    }

    @Override
    public void deleteDocument(Long caseId, Long documentId) {
        LegalCase legalCase = legalCaseRepository.findById(caseId).get();

        if (legalCase == null) {
            return;
        }

        legalCase.deleteFileById(documentId);
    }
}
