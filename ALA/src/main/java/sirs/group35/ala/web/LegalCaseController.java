package sirs.group35.ala.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.service.CaseService;
import sirs.group35.ala.web.dto.LegalCaseDTO;

@Controller
public class LegalCaseController {
    
    private final CaseService caseService;
    
    public LegalCaseController(CaseService caseService) {
        this.caseService = caseService;
    }
    
    @ModelAttribute("legalCase")
    public LegalCaseDTO legalCaseDTO() {
        return new LegalCaseDTO();
    }

    @GetMapping("/legalCase")
    public String showRegistrationForm() {
        return "legalCase";
    }

    @GetMapping("/legalCase/all")
    List<LegalCaseDTO> getAllLegalCases() {
        return caseService.listAllLegalCases();
    }

    @PostMapping("/legalCase")
    String registerCase(@ModelAttribute LegalCaseDTO newLegalCaseDTO) {
        caseService.registerLegalCase(newLegalCaseDTO);

        return "Success!";
    }

    @PostMapping("/legalCase/{title}/submit-document")
    String submitDocument(@PathVariable String title, @RequestParam("file") MultipartFile file) {
        caseService.submitDocument(title, file);

        return "Success!";
    }

    @GetMapping("/legalCase/{title}/documents")
    List<String> getDocuments(@PathVariable String title) {
        return caseService.getDocuments(title);
    }
    
    @GetMapping("/legalCase/{title}/documents/{documentName}")
    ResponseEntity<byte[]> getDocument(@PathVariable String title, @PathVariable String documentName) {
        FileDB file = caseService.getDocument(title, documentName);
        return ResponseEntity.ok()
        .header("attachment; filename=\"" + file.getName() + "\"")
        .body(file.getData());
    }
}
