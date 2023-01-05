package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "legal_case", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class LegalCase {
    
    // A case has a title, a description, a client, a lawyer and files

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<FileDB> getFiles() {
        return files;
    }

}
