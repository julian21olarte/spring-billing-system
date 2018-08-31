package com.julian21olarte.thymeleafexample.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bills")
public class Bill {
    private int id;
    private String description;
    private String observation;
    private Date createdAt;
    private Client client;
    private List<ItemBill> itemBills;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "observation", nullable = true, length = 200)
    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Basic
    @Column(name = "created_at", nullable = true, updatable= false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bill")
    public List<ItemBill> getItemBills() {
        return itemBills;
    }

    public void setItemBills(List<ItemBill> itemBills) {
        this.itemBills = itemBills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                Objects.equals(description, bill.description) &&
                Objects.equals(observation, bill.observation) &&
                Objects.equals(createdAt, bill.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, observation, createdAt);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }


    public Double calculateTotal() {
        return this.getItemBills()
                .stream()
                .mapToDouble((product) -> product.calculateTotal())
                .sum();
    }
}
