package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Entity
public class Client extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_case",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id", unique = true))
    private Collection<LegalCase> legalCases;

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String encode, Collection<Role> roles) {
        super(firstName, lastName, email, encode, roles);
        this.legalCases = new HashSet<LegalCase>();
    }


    public void addCase(LegalCase legalCase) {
        this.legalCases.add(legalCase);
    }

    public Collection<LegalCase> getLegalCase() {
        return legalCases;
    }
}
