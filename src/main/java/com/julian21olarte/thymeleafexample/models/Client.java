package com.julian21olarte.thymeleafexample.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @Email
    @NotEmpty
    private String email;

    private String photo;

    private Date createdAt;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "photo", nullable = true)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "created_at", nullable = true, updatable= false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client clients = (Client) o;
        return id == clients.id &&
                Objects.equals(name, clients.name) &&
                Objects.equals(lastname, clients.lastname) &&
                Objects.equals(email, clients.email) &&
                Objects.equals(photo, clients.photo) &&
                Objects.equals(createdAt, clients.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, email, photo, createdAt);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
}
