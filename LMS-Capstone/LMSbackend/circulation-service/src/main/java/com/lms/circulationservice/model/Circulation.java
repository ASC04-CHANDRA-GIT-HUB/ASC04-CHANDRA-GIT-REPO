package com.lms.circulationservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "circulation")
public class Circulation {
    @Id
    private String id;

    private String member_id;
    private String catalogue_id;
    private LocalDate issue_date;
    private LocalDate return_date;
    private LocalDate due_date;
    private String status; // ISSUED, RETURNED
    private boolean deleted = false;

    // getters & setters (all snake_case)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMember_id() { return member_id; }
    public void setMember_id(String member_id) { this.member_id = member_id; }

    public String getCatalogue_id() { return catalogue_id; }
    public void setCatalogue_id(String catalogue_id) { this.catalogue_id = catalogue_id; }

    public LocalDate getIssue_date() { return issue_date; }
    public void setIssue_date(LocalDate issue_date) { this.issue_date = issue_date; }

    public LocalDate getReturn_date() { return return_date; }
    public void setReturn_date(LocalDate return_date) { this.return_date = return_date; }

    public LocalDate getDue_date() { return due_date; }
    public void setDue_date(LocalDate due_date) { this.due_date = due_date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
