package com.julian21olarte.thymeleafexample.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "bill_items")
public class ItemBill {
    private long id;
    private int quantity;
    private Date createdAt;
    private Bill bill;
    private Product product;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "created_at", nullable = true, updatable= false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemBill itemBill = (ItemBill) o;
        return id == itemBill.id &&
                quantity == itemBill.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public Double calculateTotal() {
        return this.getQuantity() * this.getProduct().getPrice();
    }
}
