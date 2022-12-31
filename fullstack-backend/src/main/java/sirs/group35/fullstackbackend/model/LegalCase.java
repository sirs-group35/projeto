package sirs.group35.fullstackbackend.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class LegalCase {
    
    // A case has a title, a description, a client, a lawyer and files

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Lawyer lawyer;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, FileDB> files = new HashMap<String, FileDB>();

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
        this.client = client;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
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
