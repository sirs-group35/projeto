package sirs.group35.ala.model;

import jakarta.persistence.Entity;

import java.util.Collection;

@Entity
public class Manager extends User {

    public Manager() {
    }

    public Manager(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super(firstName, lastName, email, password, roles);
    }
}
