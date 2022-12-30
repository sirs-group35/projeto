package sirs.group35.fullstackbackend.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sirs.group35.fullstackbackend.dto.LegalCaseDTO;
import sirs.group35.fullstackbackend.dto.UserDTO;
import sirs.group35.fullstackbackend.model.LegalCase;
import sirs.group35.fullstackbackend.model.Client;
import sirs.group35.fullstackbackend.model.Lawyer;
import sirs.group35.fullstackbackend.model.User;
import sirs.group35.fullstackbackend.repository.LegalCaseRepository;
import sirs.group35.fullstackbackend.repository.ClientRepository;
import sirs.group35.fullstackbackend.repository.LawyerRepository;
import sirs.group35.fullstackbackend.repository.UserRepository;

@Service
public class CaseService {

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LegalCaseRepository legalCaseRepository;

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

}
