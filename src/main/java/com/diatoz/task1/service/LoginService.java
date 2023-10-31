package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.PasswordException;
import com.diatoz.task1.entity.AccountDetails;

import java.util.List;

public interface LoginService {
    String createAccount(AccountDetails accountDetails) throws IdException;
    String deleteAccount(AccountDetails accountDetails) throws IdException, PasswordException;
    String updatePassword(AccountDetails accountDetails) throws IdException;

    List<AccountDetails> getAllUser();


}
