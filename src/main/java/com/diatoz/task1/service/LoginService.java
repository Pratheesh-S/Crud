package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.PasswordException;
import com.diatoz.task1.model.AccountDetailsModel;

import java.util.List;

public interface LoginService {
    String createAccount(AccountDetailsModel accountDetailsModel) throws IdException;

    String deleteAccount(AccountDetailsModel accountDetailsModel) throws IdException, PasswordException;

    String updatePassword(AccountDetailsModel accountDetailsModel) throws IdException;

    List<AccountDetailsModel> getAllUser();


}
