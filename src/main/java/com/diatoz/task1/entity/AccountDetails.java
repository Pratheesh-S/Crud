package com.diatoz.task1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "accountEntity")
public class AccountDetails {
    @Id
    private String userName;
    
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
