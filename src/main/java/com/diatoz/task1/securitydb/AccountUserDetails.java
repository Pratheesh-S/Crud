package com.diatoz.task1.securitydb;

import com.diatoz.task1.entity.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class AccountUserDetails implements UserDetails {
    private final AccountDetails accountDetails;
    Logger logger = LoggerFactory.getLogger(UserDetails.class);

    public AccountUserDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<String> accountRoles = accountDetails.getRoles();
        for (String role : accountRoles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return accountDetails.getPassword();
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
