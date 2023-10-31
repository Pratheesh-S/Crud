package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.PasswordException;
import com.diatoz.task1.dao.LoginDao;
import com.diatoz.task1.entity.AccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    LoginDao loginDao;

    Logger logger  = LoggerFactory.getLogger(LoggerFactory.class);
    @Override
    public String createAccount(AccountDetails accountDetails) throws IdException, DataAccessException {
        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetails.getUserName());
        if(dataFromDb.isPresent())
        {
            logger.info(" Cannot create the account The User is already Present");
            throw new IdException("Account with given Username  Already  present");
        }

        logger.info("creating the account for the ");
        loginDao.save(accountDetails);
        return "Account created Successfully for user with Username "+ accountDetails.getUserName() ;
    }

    @Override
    public String deleteAccount(AccountDetails accountDetails) throws IdException, DataAccessException, PasswordException {
        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetails.getUserName());
        if(dataFromDb.isPresent())
        {
            if(accountDetails.getPassWord().equals(dataFromDb.get().getPassWord())) {
                loginDao.deleteById(accountDetails.getUserName());
                return "Account Deleted Successfully for user with Username " + accountDetails.getUserName();
            }
            else {
                throw new PasswordException("Given Password is wrong, please Enter correct password ");
            }

        }
        throw new IdException("Account with given user name not Present");

    }

    @Override
    public String updatePassword(AccountDetails accountDetails) throws IdException {
        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetails.getUserName());
        if(dataFromDb.isPresent())
        {
            loginDao.save(accountDetails);
            return "Account Deleted Successfully for user with Username "+ accountDetails.getUserName() ;

        }
        throw new IdException("Account with given user name not Present to update the account");

    }

    @Override
    public List<AccountDetails> getAllUser() {
        logger.info("Fetching the all the data");
        return loginDao.findAll();
    }
}
