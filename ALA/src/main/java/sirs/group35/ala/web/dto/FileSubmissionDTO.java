package sirs.group35.ala.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileSubmissionDTO {
    Long caseId;

    MultipartFile file;

    public FileSubmissionDTO() {
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
