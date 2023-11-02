package com.diatoz.task1.securitydb;

import com.diatoz.task1.entity.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountUserDetails implements UserDetails {
    Logger logger = LoggerFactory.getLogger(UserDetails.class);
    private final AccountDetails accountDetails;

    public AccountUserDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
        logger.info("Authentication on UserDetails ");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("Admin"));
    }


    @Override
    public String getPassword() {
        return accountDetails.getPassWord();
    }

    @Override
    public String getUsername() {
        return accountDetails.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
