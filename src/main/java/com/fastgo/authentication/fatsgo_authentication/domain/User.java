package com.fastgo.authentication.fatsgo_authentication.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private State status;
    private Role role;
    private boolean isSynchronized;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public State getStatus() {
        return status;
    }
    public void setStatus(State status) {
        this.status = status;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isSynchronized() {
        return isSynchronized;
    }
    public void setSynchronized(boolean aSynchronized) {
        isSynchronized = aSynchronized;
    }
}