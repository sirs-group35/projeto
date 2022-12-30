package sirs.group35.fullstackbackend.dto;

public class CaseDTO {
    private String title;
    private String description;
    private UserDTO client;
    private UserDTO lawyer;
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

    public UserDTO getClient() {
        return client;
    }

    public void setClient(UserDTO client) {
        this.client = client;
    }

    public UserDTO getLawyer() {
        return lawyer;
    }

    public void setLawyer(UserDTO lawyer) {
        this.lawyer = lawyer;
    }

    public int getSubmittedFiles() {
        return submittedFiles;
    }

    public void setSubmittedFiles(int submittedFiles) {
        this.submittedFiles = submittedFiles;
    }

}
