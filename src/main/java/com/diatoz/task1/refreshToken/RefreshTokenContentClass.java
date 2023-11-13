package com.diatoz.task1.refreshToken;

import com.diatoz.task1.model.JwtResponse;
import com.diatoz.task1.security.JwtHelper;
import com.diatoz.task1.security.JwtRefreshHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class RefreshTokenContentClass {
    @Autowired
    JwtRefreshHelper jwtRefreshHelper;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtHelper jwtHelper;
    Logger logger = LoggerFactory.getLogger(RefreshTokenContentClass.class);

    public JwtResponse getAccessTokenFromRefreshToken(String token, Cookie[] cookies, HttpServletResponse httpResponse,String userName) {
        String refreshToken = getRefreshTokenFromCookies(cookies,userName);
        String refreshUserName = null;
        try {
            refreshUserName = jwtRefreshHelper.getUsernameFromRefreshToken(refreshToken);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshUserName);
        String newToken = jwtHelper.generateToken(userDetails, httpResponse);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setUserName(refreshUserName);
        jwtResponse.setJwtToken(newToken);
        return jwtResponse;


    }

    private String getRefreshTokenFromCookies(Cookie[] cookies, String userName) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(userName)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
