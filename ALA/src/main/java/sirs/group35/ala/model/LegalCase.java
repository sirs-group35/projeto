package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "legal_case", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class LegalCase {

    // A case has a title, a description, a client, a lawyer and files

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Lawyer lawyer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "case_file",
            joinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "file_id", referencedColumnName = "id", unique = true))
    private Collection<FileDB> files = new HashSet<FileDB>();

    public UUID getId() {
        return id;
    }

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        System.out.println("Client: " + client.toString() + ", ID: " + client.getId().toString());
        this.client = client;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        System.out.println("Lawyer: " + lawyer.toString() + ", ID: " + lawyer.getId().toString());
        this.lawyer = lawyer;
    }

    public void addFile(FileDB file) {
        this.files.add(file);
    }

    public void deleteFileById(UUID id) {
        for (FileDB file : this.files) {
            if (file.getId().equals(id)) {
                this.files.remove(file);
                break;
            }
        }
    }

    public Collection<FileDB> getFiles() {
        return files;
    }

    // Get string with all file names separated by line breaks
    public String getFilesString() {
        String filesString = "";
        for (FileDB file : files) {
            filesString += file.getName() + "\n";
        }
        return filesString;
    }
}
