package com.vls.login.model;

import javax.persistence.*;

@Entity
@Table(name = "Login")
public class LoginModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoginId")
    private Integer loginId;

    @Column(name = "UserName", nullable = false)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;

    public LoginModel() {}

    public LoginModel(Integer loginId,String username, String password) {
        this.loginId = loginId;
        this.username=username;
        this.password = password;
    }

    public Integer getLoginId() { return loginId; }
    public void setLoginId(Integer loginId) { this.loginId = loginId; }

    public String getUserName() { return username; }
    public void setUserName(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
