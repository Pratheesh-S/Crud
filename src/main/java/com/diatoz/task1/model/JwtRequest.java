package com.diatoz.task1.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class JwtRequest {

    @Id
    @NotBlank(message = "{accountDetails.username.blank}")
    @Length(min = 5, message = "{accountDetails.username.length}")
    String userName;

    @NotBlank(message = "{accountDetails.password.blank}")
    String password;

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
}
