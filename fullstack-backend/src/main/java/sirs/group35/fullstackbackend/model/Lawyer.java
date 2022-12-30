package sirs.group35.fullstackbackend.model;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lawyer extends User {

    HashMap<String, LegalCase> legalCases;

    public void addCase(LegalCase legalCase) {
        this.legalCases.put(legalCase.getTitle(), legalCase);
    }

    public HashMap<String, LegalCase> getLegalCase() {
        return legalCases;
    }

    public void getCaseFiles(String title) {
        legalCases.get(title).getFiles();
    }    
}
