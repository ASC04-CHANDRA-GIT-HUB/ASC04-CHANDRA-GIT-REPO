package com.lms.acquisitionservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "acquisition")
public class Acquisition {

    @Id
    private String id;
    private String title;
    private String author;
    private String supplier;
    private String status; // PENDING, RECEIVED
    private boolean deleted = false;
    private String description;

    // Constructors
    public Acquisition() {}

    public Acquisition(String id, String title, String author, String supplier, String status, boolean deleted, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.supplier = supplier;
        this.status = status;
        this.deleted = deleted;
        this.description = description;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
