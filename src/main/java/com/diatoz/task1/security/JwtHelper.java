package com.diatoz.task1.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtHelper {
    //requirement :
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public final Logger logger = LoggerFactory.getLogger(JwtHelper.class);

    //    public static final long JWT_TOKEN_VALIDITY =  60;

    private final String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        logger.info("Claims = {}", claims);
        return claimsResolver.apply(claims);
    }

    public List<SimpleGrantedAuthority> getAuthority(String token)
    {
        final Claims claims = getAllClaimsFromToken(token);
        Object data = claims.get("roles");
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (data instanceof List) {
            List<Map<String, String>> authorityData = (List<Map<String, String>>) data;
            for (Map<String, String> authorityMap : authorityData) {
                if (authorityMap.containsKey("authority")) {
                    String authority = authorityMap.get("authority");
                    authorities.add(new SimpleGrantedAuthority(authority));
                }
            }
        }
        logger.info("data of authorities {}",authorities);


        return authorities;

    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        logger.info("Expiration timen={}", expiration);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())  && !isTokenExpired(token));
    }

//    private boolean checkTheAuthority(Collection<? extends GrantedAuthority> authorities, List<SimpleGrantedAuthority> authority) {
//
//        Set<String> authoritySet = authorities.stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toSet());
//
//        // Check if all authorities from the authority list are present in the authority set
//        for (SimpleGrantedAuthority simpleGrantedAuthority : authority) {
//            if (authoritySet.contains(simpleGrantedAuthority.getAuthority())) {
//                return true;
//            }
//        }
//
//        return true;
//    }


}
