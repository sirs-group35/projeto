package sirs.group35.ala.model;

import jakarta.persistence.Entity;

import java.util.Collection;

@Entity
public class Client extends User {

    public Client() {
    }

    public Client(String firstName, String lastName, String email, String encode, Collection<Role> roles) {
        super(firstName, lastName, email, encode, roles);
    }
}
