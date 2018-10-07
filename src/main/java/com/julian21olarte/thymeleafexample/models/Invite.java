package com.julian21olarte.thymeleafexample.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "invites")
public class Invite {
    private long id;
    private String message;
    private Client client;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invite invite = (Invite) o;
        return id == invite.id &&
                client == invite.client &&
                Objects.equals(message, invite.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, client);
    }
}
