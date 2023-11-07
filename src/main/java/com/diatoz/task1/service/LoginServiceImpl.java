package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.PasswordException;
import com.diatoz.task1.dao.LoginDao;
import com.diatoz.task1.entity.AccountDetails;
import com.diatoz.task1.model.AccountDetailsModel;
import com.diatoz.task1.password.AccountPassEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;
    @Autowired
    AccountPassEncoder accountPassEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Override
    public String createAccount(AccountDetailsModel accountDetailsModel) throws IdException, DataAccessException {
        AccountDetails accountDetails = accountBeanToEntity(accountDetailsModel);

        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetailsModel.getUserName());
        if (dataFromDb.isPresent()) {
            logger.info(" Cannot create the account The User is already Present");
            throw new IdException("Account with given Username  Already  present");
        }
        accountDetails.setPassword(passwordEncoder.encode(accountDetails.getPassword()));

        loginDao.save(accountDetails);
        return "Account created Successfully for user with Username " + accountDetailsModel.getUserName();
    }

    @Override
    public String deleteAccount(AccountDetailsModel accountDetailsModel) throws IdException, DataAccessException, PasswordException {
        AccountDetails accountDetails = accountBeanToEntity(accountDetailsModel);

        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetails.getUserName());
        if (dataFromDb.isPresent()) {
            if (passwordEncoder.matches(accountDetails.getPassword(), dataFromDb.get().getPassword())) {
                loginDao.deleteById(accountDetailsModel.getUserName());
                return "Account Deleted Successfully for user with Username " + accountDetailsModel.getUserName();
            } else {
                throw new PasswordException("Given Password is wrong, please Enter correct password ");
            }

        }
        throw new IdException("Account with given user name not Present");

    }

    @Override
    public String updatePassword(AccountDetailsModel accountDetailsModel) throws IdException {
        AccountDetails accountDetails = accountBeanToEntity(accountDetailsModel);

        Optional<AccountDetails> dataFromDb = loginDao.findById(accountDetails.getUserName());
        if (dataFromDb.isPresent()) {


            loginDao.save(accountPassEncoder.encodePasswordAccount(accountDetails));
            return "Password updated Successfully for user with Username " + accountDetailsModel.getUserName();

        }
        throw new IdException("Account with given user name not Present to update the account");

    }


    @Override
    public List<AccountDetailsModel> getAllUser() {
        logger.info("Fetching the all the data");
        List<AccountDetails> allAccountDetails = loginDao.findAll();
        List<AccountDetailsModel> allAccountDetailsModel = new ArrayList<>();
        for (AccountDetails accountDetails : allAccountDetails) {
            allAccountDetailsModel.add(accountEntityToBean(accountDetails));
        }
        return allAccountDetailsModel;
    }

    public AccountDetails accountBeanToEntity(AccountDetailsModel accountDetailsModel) {
        AccountDetails accountDetails = new AccountDetails();
        BeanUtils.copyProperties(accountDetailsModel, accountDetails);
        return accountDetails;
    }

    public AccountDetailsModel accountEntityToBean(AccountDetails accountDetails) {
        AccountDetailsModel accountDetailsModel = new AccountDetailsModel();
        BeanUtils.copyProperties(accountDetails, accountDetailsModel);
        return accountDetailsModel;
    }
}
