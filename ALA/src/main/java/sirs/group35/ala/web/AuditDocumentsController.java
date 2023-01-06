package sirs.group35.ala.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sirs.group35.ala.model.FileDB;
import sirs.group35.ala.repository.FileDBRepository;
import sirs.group35.ala.util.Auditor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping()
public class AuditDocumentsController {

    private final FileDBRepository fileDBRepository;

    public AuditDocumentsController(FileDBRepository fileDBRepository) {
        this.fileDBRepository = fileDBRepository;
    }

    @GetMapping("/manager/audit-documents")
    public ModelAndView auditDocuments() {

        ModelAndView mav = new ModelAndView("audit-documents");

        mav.addObject("files", fileDBRepository.findAll());

        return mav;
    }

    @PostMapping("/manager/audit-documents/validate")
    public ModelAndView auditedDocuments(@RequestParam("file") MultipartFile publicKeyFile) {

        ModelAndView mav = new ModelAndView("audited-documents");

        List<FileDB> files = fileDBRepository.findAll();
        List<Boolean> auditory = new ArrayList<Boolean>();

        try {
            String publicKey = new String(publicKeyFile.getBytes(), StandardCharsets.UTF_8);
            publicKey = publicKey.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");

            System.out.println("PUBLIC KEY: " + publicKey);

            Auditor auditor = new Auditor(publicKey);

            for (FileDB file : files) auditory.add(auditor.validateDocument(file));

            System.out.println("bruh\n\n\n\n\n");

            mav.addObject("files", files);
            mav.addObject("auditory", auditory);

            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/manager/audit-documents?documentValidationFailed");
        }
    }

}
