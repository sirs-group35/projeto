package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "legal_case", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
public class LegalCase {
    
    // A case has a title, a description, a client, a lawyer and files

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Lawyer lawyer;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, FileDB> files = new HashMap<String, FileDB>();

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

    public FileDB getFile(String name) {
        return files.get(name);
    }

    public void addFile(String fileName, FileDB file) {
        this.files.put(fileName, file);
    }

    public Map<String, FileDB> getFiles() {
        return files;
    }

}
