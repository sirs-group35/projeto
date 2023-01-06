package sirs.group35.ala.web;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.repository.FileDBRepository;

@Controller
@RequestMapping("/manager/audit-documents")
public class AuditDocumentsController {

    private final FileDBRepository fileDBRepository;

    public AuditDocumentsController(FileDBRepository fileDBRepository) {
        this.fileDBRepository = fileDBRepository;
    }

    @GetMapping()
    public ModelAndView auditDocuments() {

        ModelAndView mav = new ModelAndView("audit-documents");

        mav.addObject("files", fileDBRepository.findAll());

        return mav;
    }

}
