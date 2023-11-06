package com.diatoz.task1.model;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

public class AccountDetailsModel {
    @NotBlank(message = "{accountDetails.username.blank}")
    @Length(min = 5, message = "{accountDetails.username.length}")
    private String userName;

    @NotBlank(message = "{accountDetails.password.blank}")
    @Length(min = 6, message = "{accountDetails.password.min}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$", message = "{accountDetails.password.pattern}")
    private String password;


    @NotNull(message="{accountDetails.roles.notNull}")
    @Size(min = 1, message = "At least one role must be selected")
    private Set<String> roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AccountDetailsModel{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
