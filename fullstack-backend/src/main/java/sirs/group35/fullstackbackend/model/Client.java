package sirs.group35.fullstackbackend.model;

import java.io.File;
import java.util.HashMap;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

    HashMap<String, Case> legalCases;

    public void addCase(Case legalCase) {
        this.legalCases.put(legalCase.getTitle(), legalCase);
    }

    public HashMap<String, Case> getLegalCase() {
        return legalCases;
    }

    public void submitFile(String title, File file) {
        legalCases.get(title).getFiles().put(file.getName(), file);
    }
}