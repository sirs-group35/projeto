package sirs.group35.ala.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Lawyer extends User {

    public Lawyer() {
    }

    public Lawyer(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super(firstName, lastName, email, password, roles);
    }
}
