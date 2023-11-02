package com.diatoz.task1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "account")
public class AccountDetails {

    @Id
    @NotBlank(message = "{accountDetails.username.blank}")
    @Length(min = 5, message = "{accountDetails.username.length}")
    private String userName;

    @NotBlank(message = "{accountDetails.password.blank}")
    @Column(name = "password")
    @Length(min = 6, message = "{accountDetails.password.min}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$", message = "{accountDetails.password.pattern}")

    private String passeword;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passeword;
    }

    public void setPassWord(String passeword) {
        this.passeword = passeword;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "userName='" + userName + '\'' +
                ", passeword='" + passeword + '\'' +
                '}';
    }
}
