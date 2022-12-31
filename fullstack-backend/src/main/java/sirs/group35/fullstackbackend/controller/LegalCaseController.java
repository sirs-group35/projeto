package sirs.group35.fullstackbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sirs.group35.fullstackbackend.dto.LegalCaseDTO;
import sirs.group35.fullstackbackend.model.FileDB;
import sirs.group35.fullstackbackend.services.CaseService;

@RestController
@CrossOrigin("http://localhost:3000")
public class LegalCaseController {
    
    @Autowired
    private CaseService caseService = new CaseService();
    
    @GetMapping("/legalCases")
    List<LegalCaseDTO> getAllLegalCases() {
        return caseService.listAllLegalCases();
    }

    @PostMapping("/legalCase")
    LegalCaseDTO registerCase(@RequestBody LegalCaseDTO newLegalCaseDTO) {
        return caseService.registerLegalCase(newLegalCaseDTO);
    }

    @PostMapping("/legalCase/{title}/submit-document")
    String submitDocument(@PathVariable String title, @RequestParam("file") MultipartFile file) {
        return caseService.submitDocument(title, file);
    }

    @GetMapping("/legalCase/{title}/documents")
    List<String> getDocuments(@PathVariable String title) {
        return caseService.getDocuments(title);
    }
    
    @GetMapping("/legalCase/{title}/documents/{documentName}")
    ResponseEntity<byte[]> getDocument(@PathVariable String title, @PathVariable String documentName) {
        FileDB file = caseService.getDocument(title, documentName);
        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
        .body(file.getData());
    }
}
