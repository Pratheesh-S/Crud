package com.diatoz.task1.password;

import com.diatoz.task1.entity.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountPassEncoder {

    @Autowired
    PasswordEncoder passwordEncoder;

    public AccountDetails encodePasswordAccount(AccountDetails accountDetails) {
        accountDetails.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
        return accountDetails;

    }

}
