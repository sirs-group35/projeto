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

    HashMap<String, LegalCase> legalCases;

    public void addCase(LegalCase legalCase) {
        this.legalCases.put(legalCase.getTitle(), legalCase);
    }

    public HashMap<String, LegalCase> getLegalCase() {
        return legalCases;
    }


}