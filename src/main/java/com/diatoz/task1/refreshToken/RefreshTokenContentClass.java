package com.diatoz.task1.refreshToken;

import com.diatoz.task1.customException.DataRelatedException;
import com.diatoz.task1.model.JwtResponse;
import com.diatoz.task1.security.JwtHelper;
import com.diatoz.task1.security.JwtRefreshHelper;
import com.diatoz.task1.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class RefreshTokenContentClass {
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JwtRefreshHelper jwtRefreshHelper;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtHelper jwtHelper;
    Logger logger = LoggerFactory.getLogger(RefreshTokenContentClass.class);

    public JwtResponse getAccessTokenFromRefreshToken(String userName) throws DataRelatedException {
        String refreshToken = getRefreshTokenFromDb(userName);
        String refreshUserName = null;
        try {
            refreshUserName = jwtRefreshHelper.getUsernameFromRefreshToken(refreshToken);
        } catch (Exception e) {

            logger.info(e.getMessage());
            Map<String,String> error = new HashMap<>();
            error.put("error ", e.getMessage());
            error.put("message","Session ended : Please login with username and password");
            throw new DataRelatedException(error);

        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUserName);
        String newToken = jwtHelper.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUserName(refreshUserName);
        jwtResponse.setJwtToken(newToken);
        return jwtResponse;


    }

    private String getRefreshTokenFromDb( String userName) throws DataRelatedException {
        return refreshTokenService.getRefreshToken(userName);

    }
}
