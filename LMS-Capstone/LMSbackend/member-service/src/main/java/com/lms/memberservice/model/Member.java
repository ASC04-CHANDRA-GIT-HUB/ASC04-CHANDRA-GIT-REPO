package com.lms.memberservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Member {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String phone;
    private boolean deleted = false;

    public Member() {}
    public Member(String id, String name, String email, String phone) {
        this.id = id; this.name = name; this.email = email; this.phone = phone;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
