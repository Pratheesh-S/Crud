package com.diatoz.task1.securitydb;

import com.diatoz.task1.dao.LoginDao;
import com.diatoz.task1.entity.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoadUserAccountData implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
    @Autowired
    LoginDao loginDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("load the user by user name for user {} ", username);
        Optional<AccountDetails> accountDetailsFromDb = loginDao.findById(username);
        if (accountDetailsFromDb.isPresent()) {

            return new AccountUserDetails(accountDetailsFromDb.get());
        }
        logger.error("Username is not found in database");
        throw new UsernameNotFoundException("User With given Username not  present in the database, please provide valid data");
    }
}
