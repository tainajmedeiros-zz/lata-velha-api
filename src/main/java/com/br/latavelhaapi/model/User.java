package com.br.latavelhaapi.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    @NotNull
    @Size(max=100)
    private String name;

    @Column
    @Email
    private String email;

    @Column
    @NotNull
    private String password;

    @Column
    private String token;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setToken() {
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        this.token = token;
    }
}
