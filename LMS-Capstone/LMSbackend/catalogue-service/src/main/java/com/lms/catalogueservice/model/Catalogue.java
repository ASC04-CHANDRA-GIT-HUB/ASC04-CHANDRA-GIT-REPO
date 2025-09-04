package com.lms.catalogueservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Catalogue {

    @Id
    private String id;

    private String title;
    private String author;
    private boolean deleted = false;
    private String description;

    private boolean available = true;

    @Column(nullable = false)
    private Double rating = 0.0;   // ✅ new column for average rating

    // --- getters & setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
