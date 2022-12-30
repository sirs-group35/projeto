package sirs.group35.fullstackbackend.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sirs.group35.fullstackbackend.dto.CaseDTO;
import sirs.group35.fullstackbackend.dto.UserDTO;
import sirs.group35.fullstackbackend.model.Case;
import sirs.group35.fullstackbackend.model.Client;
import sirs.group35.fullstackbackend.model.Lawyer;
import sirs.group35.fullstackbackend.model.User;
import sirs.group35.fullstackbackend.repository.CaseRepository;
import sirs.group35.fullstackbackend.repository.ClientRepository;
import sirs.group35.fullstackbackend.repository.LawyerRepository;
import sirs.group35.fullstackbackend.repository.UserRepository;

public class CaseService {

    // @Autowired
    // private UserRepository userRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CaseRepository caseRepository;

    public List<CaseDTO> listAllCases() {
        List<Case> cases = caseRepository.findAll();
        List<CaseDTO> caseDTOs = new ArrayList<>();
        for (Case legalCase : cases) {
            CaseDTO caseDTO = new CaseDTO();
            caseDTO.setTitle(legalCase.getTitle());
            UserDTO client = new UserDTO();
            client.setUsername(legalCase.getClient().getUsername());
            client.setName(legalCase.getClient().getName());
            client.setEmail(legalCase.getClient().getEmail());
            client.setLawyer(false);
            caseDTO.setClient(client);
            UserDTO lawyer = new UserDTO();
            lawyer.setUsername(legalCase.getLawyer().getUsername());
            lawyer.setName(legalCase.getLawyer().getName());
            lawyer.setEmail(legalCase.getLawyer().getEmail());
            lawyer.setLawyer(true);
            caseDTO.setLawyer(lawyer);
            caseDTO.setSubmittedFiles(legalCase.getFiles().size());
            caseDTOs.add(caseDTO);
        }
        return caseDTOs;
    }

    public CaseDTO registerCase(CaseDTO newCaseDTO) {
        Case newCase = new Case();
        newCase.setTitle(newCaseDTO.getTitle());
        Client client = clientRepository.findByUsername(newCaseDTO.getClient().getUsername());
        newCase.setClient(client);
        Lawyer lawyer = lawyerRepository.findByUsername(newCaseDTO.getLawyer().getUsername());
        newCase.setLawyer(lawyer);
        caseRepository.save(newCase);
        return newCaseDTO;
    }

}
