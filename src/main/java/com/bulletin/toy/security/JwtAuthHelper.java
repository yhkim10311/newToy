package com.bulletin.toy.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtAuthHelper {

    public static final long ACCESS_TOKEN_VALIDITY = 60; //one minutes
    public static final long REFRESH_TOKEN_VALIDITY = 7*24*60*60; //one week
    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${jwt.token.secret}")
    private String secret;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token){
        try{
            Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch(ExpiredJwtException e){
            return false;
        }
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsRsolver){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsRsolver.apply(claims);
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateAccessToken(UserDetails userDetails){
        return generateToken(userDetails, ACCESS_TOKEN_VALIDITY);
    }

    public String generateRefreshToken(UserDetails userDetails){
        return generateToken(userDetails, REFRESH_TOKEN_VALIDITY);
    }

    public String generateToken(UserDetails userDetails, long validity){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDetails.getAuthorities());
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
