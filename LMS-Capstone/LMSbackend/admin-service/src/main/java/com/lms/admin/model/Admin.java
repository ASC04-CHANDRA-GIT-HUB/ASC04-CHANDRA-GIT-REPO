package com.lms.admin.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name="admins")
public class Admin {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    @Email @NotBlank
    private String email;
    @NotBlank private String name;
    @NotBlank private String phone;
    @NotBlank private String passwordHash;
    private boolean disabled=false;
    private int failedAttempts=0;
    private LocalDateTime lockoutTime;
    // getters and setters
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
    public String getPasswordHash(){return passwordHash;} public void setPasswordHash(String p){this.passwordHash=p;}
    public boolean isDisabled(){return disabled;} public void setDisabled(boolean d){this.disabled=d;}
    public int getFailedAttempts(){return failedAttempts;} public void setFailedAttempts(int f){this.failedAttempts=f;}
    public LocalDateTime getLockoutTime(){return lockoutTime;} public void setLockoutTime(LocalDateTime t){this.lockoutTime=t;}
}
