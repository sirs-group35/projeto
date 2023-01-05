package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
public class Lawyer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "lawyer_case",
            joinColumns = @JoinColumn(name = "lawyer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "case_id", referencedColumnName = "id", unique = true))
    private Collection<LegalCase> legalCases;

    public Lawyer() {
    }

    public Lawyer(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super(firstName, lastName, email, password, roles);
        this.legalCases = new HashSet<LegalCase>();
    }

    public boolean hasCase(LegalCase legalCase) {
        return legalCases.contains(legalCase);
    }

    public void addCase(LegalCase legalCase) {
        this.legalCases.add(legalCase);
    }

    public void removeCase(LegalCase legalCase) {
        this.legalCases.remove(legalCase);
    }

    public Collection<LegalCase> getLegalCases() {
        return legalCases;
    }
}
