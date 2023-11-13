package com.diatoz.task1.service;

import com.diatoz.task1.customException.DataRelatedException;
import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.entity.RefreshTokenEntity;

public interface RefreshTokenService {

    String getRefreshToken(String userName) throws DataRelatedException;

    void storeRefreshToken(RefreshTokenEntity refreshTokenEntity);

}
