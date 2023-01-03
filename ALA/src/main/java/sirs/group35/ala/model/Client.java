package sirs.group35.ala.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private Map<String, LegalCase> legalCases;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String encode, Collection<Role> roles) {
        super(firstName, lastName, email, encode, roles);
        this.legalCases = new HashMap<String, LegalCase>();
    }


    public void addCase(LegalCase legalCase) {
        this.legalCases.put(legalCase.getTitle(), legalCase);
    }

    public Map<String, LegalCase> getLegalCase() {
        return legalCases;
    }
}
