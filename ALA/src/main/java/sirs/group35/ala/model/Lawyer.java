package sirs.group35.ala.model;

import jakarta.persistence.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Lawyer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, LegalCase> legalCases;

    public Lawyer() {
    }

    public Lawyer(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super(firstName, lastName, email, password, roles);
        this.legalCases = new HashMap<String, LegalCase>();
    }

    public void addCase(LegalCase legalCase) {
        this.legalCases.put(legalCase.getTitle(), legalCase);
    }

    public Map<String, LegalCase> getLegalCase() {
        return legalCases;
    }
}
