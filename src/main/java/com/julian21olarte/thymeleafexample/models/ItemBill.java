package com.julian21olarte.thymeleafexample.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bill_items")
public class ItemBill {
    private long id;
    private int quantity;
    private Bill bill;

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

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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
}
