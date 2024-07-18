package com.example.Medicine.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicine")
public class InventoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String batch;

    @Column(nullable = false)
    private String quality;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

  
    public InventoryModel() {
    }

 
    public InventoryModel(String name, String batch, String quality, double price, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.batch = batch;
        this.quality = quality;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    
    @Override
    public String toString() {
        return "InventoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", batch='" + batch + '\'' +
                ", quality='" + quality + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
