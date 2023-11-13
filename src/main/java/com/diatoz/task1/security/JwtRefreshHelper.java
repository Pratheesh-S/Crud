package com.diatoz.task1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtRefreshHelper {

    public static long JWT_REFRESH_TOKEN_VALIDITY = 5 * 60 *60;
    public final Logger logger = LoggerFactory.getLogger(JwtRefreshHelper.class);


    private final String refreshSecret = "bcbcbcbcbhdhdhdehoehouoehoeuheuhouhAbibabOJOBABHHIJOIJOIJOHIOPJPIJPIJPIPJPIJIJIJOJEJUHHIUHEIUH";


    //retrieve username from jwt token
    public String getUsernameFromRefreshToken(String token) {
        return getClaimFromRefreshToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromRefreshToken(String token) {
        return getClaimFromRefreshToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromRefreshToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromRefreshToken(token);
        logger.info("Claims = {}", claims);
        return claimsResolver.apply(claims);
    }


    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromRefreshToken(String token) {
        return Jwts.parser().setSigningKey(refreshSecret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromRefreshToken(token);
        logger.info("Expiration timen={}", expiration);
        return expiration.before(new Date());
    }



    public String generateRefreshToken(UserDetails userDetails, HttpServletResponse httpServletResponse)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        String refreshToken = doGenerateRefreshToken(claims,userDetails.getUsername(),httpServletResponse);
        return refreshToken;

    }

    private String doGenerateRefreshToken(Map<String, Object> claims, String username,HttpServletResponse httpServletResponse) {
        String token =  Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + JWT_REFRESH_TOKEN_VALIDITY * 1000) )
                .signWith(SignatureAlgorithm.HS512, refreshSecret).compact();
        storeInCookies(token,httpServletResponse,username);
        return token;
    }

    private void storeInCookies(String token, HttpServletResponse httpResponse,String userName) {
        Cookie myCookie = new Cookie(userName, token);
        myCookie.setPath("/");
        logger.info("Refresh token set at Cookies   {}", myCookie.getValue());
        myCookie.setMaxAge(3600 * 60); // Cookie will expire in 1 hour
        httpResponse.addCookie(myCookie);

    }


    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromRefreshToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
