package sirs.group35.fullstackbackend.dto;

public class LegalCaseDTO {
    private String title;
    private String description;
    private String client;
    private String lawyer;
    private int submittedFiles;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLawyer() {
        return lawyer;
    }

    public void setLawyer(String lawyer) {
        this.lawyer = lawyer;
    }

    public int getSubmittedFiles() {
        return submittedFiles;
    }

    public void setSubmittedFiles(int submittedFiles) {
        this.submittedFiles = submittedFiles;
    }

}
