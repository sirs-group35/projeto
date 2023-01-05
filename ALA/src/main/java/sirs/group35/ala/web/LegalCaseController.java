package sirs.group35.ala.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sirs.group35.ala.model.*;
import sirs.group35.ala.repository.*;
import sirs.group35.ala.service.CaseService;
import sirs.group35.ala.web.dto.FileSubmissionDTO;
import sirs.group35.ala.web.dto.LegalCaseDTO;
import sirs.group35.ala.web.dto.UserRegistrationDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class LegalCaseController {
    
    private final CaseService caseService;
    private final LegalCaseRepository legalCaseRepository;
    private final LawyerRepository lawyerRepository;

    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public LegalCaseController(CaseService caseService,
                               LegalCaseRepository legalCaseRepository,
                               LawyerRepository lawyerRepository, RoleRepository roleRepository,
                               ClientRepository clientRepository,
                               UserRepository userRepository) {
        this.caseService = caseService;
        this.legalCaseRepository = legalCaseRepository;
        this.lawyerRepository = lawyerRepository;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }
    
    @ModelAttribute("legalCase")
    public LegalCaseDTO legalCaseDTO() {
        return new LegalCaseDTO();
    }

    @GetMapping("/legalCase/create")
    public String showRegistrationForm() {
        return "create-case";
    }

    @PostMapping("/legalCase/create")
    String registerCase(@ModelAttribute LegalCaseDTO newLegalCaseDTO) {
        caseService.registerLegalCase(newLegalCaseDTO);

        return "redirect:/legalCase/list?createSuccess";
    }

    // Delete Case
    @GetMapping("/legalCase/delete/{id}")
    public String deleteCase(@PathVariable("id") Long id) {
        Optional<LegalCase> legalCaseOpt = legalCaseRepository.findById(id);

        LegalCase legalCase = legalCaseOpt.get();

        Lawyer lawyer = legalCase.getLawyer();
        Client client = legalCase.getClient();

        lawyer.removeCase(legalCase);
        client.removeCase(legalCase);

        legalCaseRepository.deleteById(id);
        return "redirect:/legalCase/list?deleteSuccess";
    }

    @GetMapping("/legalCase/list")
    public ModelAndView getLawyerLegalCases() {
        // Get current authenticated user
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        ModelAndView mav = new ModelAndView("list-cases");

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            User user = userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername());

            Collection<Role> userRoles = user.getRoles();

            if (userRoles.contains(roleRepository.findByName("ROLE_MANAGER"))) {
                mav.addObject("cases", legalCaseRepository.findAll());
            } else if (userRoles.contains(roleRepository.findByName("ROLE_LAWYER"))) {
                mav.addObject("cases", lawyerRepository.findByEmail(user.getEmail()).getLegalCases());
            } else if (userRoles.contains(roleRepository.findByName("ROLE_CLIENT"))) {
                mav.addObject("cases", clientRepository.findByEmail(user.getEmail()).getLegalCases());
            }
        }

        return mav;
    }

    @GetMapping("/legalCase/submit-document/{id}")
    ModelAndView showSubmitDocumentForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("submit-document");
        mav.addObject("caseId", id);
        return mav;
    }

    @PostMapping("/legalCase/submit-document/{id}")
    String submitDocument(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) {
        // Get current authenticated user
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        // Get case by id
        Optional<LegalCase> legalCaseOpt = legalCaseRepository.findById(id);

        if (legalCaseOpt.isEmpty()) {
            return "redirect:/legalCase/list?invalidCase";
        }

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            User user = userRepository.findByEmail(((UserDetails) authentication.getPrincipal()).getUsername());

            Collection<Role> userRoles = user.getRoles();

            if (userRoles.contains(roleRepository.findByName("ROLE_MANAGER"))) {
                caseService.submitDocument(id, file);
            } else if (userRoles.contains(roleRepository.findByName("ROLE_LAWYER"))) {
                // Cast user to lawyer
                Lawyer lawyer = lawyerRepository.findByEmail(user.getEmail());
                if (lawyer.hasCase(legalCaseOpt.get())) {
                    caseService.submitDocument(id, file);
                } else {
                    return "redirect:/legalCase/list?invalidAccess";
                }
            }else if (userRoles.contains(roleRepository.findByName("ROLE_CLIENT"))) {
                // Cast user to client
                Client client = clientRepository.findByEmail(user.getEmail());
                if (client.hasCase(legalCaseOpt.get())) {
                    caseService.submitDocument(id, file);
                } else {
                    return "redirect:/legalCase/list?invalidAccess";
                }
            }
        }

        return "redirect:/legalCase/list?fileSubmitSuccess";
    }

    @GetMapping("/legalCase/documents/{id}")
    List<String> getDocuments(@PathVariable Long id) {
        return caseService.getDocuments(id);
    }
    
    @GetMapping("/legalCase/documents//{id}/{documentName}")
    ResponseEntity<byte[]> getDocument(@PathVariable Long id, @PathVariable String documentName) {
        FileDB file = caseService.getDocument(id, documentName);
        return ResponseEntity.ok()
        .header("attachment; filename=\"" + file.getName() + "\"")
        .body(file.getData());
    }
}
