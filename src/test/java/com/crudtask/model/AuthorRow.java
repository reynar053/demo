package com.crudtask.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

@Entity
@Table(name = "author")
public class AuthorRow implements ITableRow {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login")
    @NaturalId
    private String login;
    @Column(name = "email")
    @NaturalId
    private String email;

    public AuthorRow() {
    }

    public AuthorRow(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorRow authorRow)) return false;
        return Objects.equals(name, authorRow.name) && Objects.equals(login, authorRow.login) && Objects.equals(email, authorRow.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }
}
