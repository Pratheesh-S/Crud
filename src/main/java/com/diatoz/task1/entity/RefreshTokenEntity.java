package com.diatoz.task1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class RefreshTokenEntity {

    @Id
    private String username;
    private String refreshToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
