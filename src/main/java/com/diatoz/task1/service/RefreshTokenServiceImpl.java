package com.diatoz.task1.service;

import com.diatoz.task1.customException.DataNotProper;
import com.diatoz.task1.customException.DataRelatedException;
import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.dao.RefreshTokenDao;
import com.diatoz.task1.entity.RefreshTokenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RefreshTokenServiceImpl implements  RefreshTokenService{

    Logger logger = LoggerFactory.getLogger(RefreshTokenServiceImpl.class);
    @Autowired
    RefreshTokenDao refreshTokenDao;
    @Override
    public String getRefreshToken(String userName) throws DataRelatedException {
        Optional<RefreshTokenEntity> refreshToken = refreshTokenDao.findById(userName);
        if(refreshToken.isPresent())
        {
            logger.info("Getting the Refresh Token from database");
            return refreshToken.get().getRefreshToken();
        }
        logger.info("Unable to find the refresh token of username");
        Map<String,String> error = new HashMap<>();
        error.put("error","The Refresh Token for given Id is not Present, Try to login ");
        throw  new DataRelatedException(error);
    }

    @Override
    public void storeRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        logger.info("The Refresh token is store in database");
        refreshTokenDao.save(refreshTokenEntity);

    }
}
