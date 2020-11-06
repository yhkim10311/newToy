package com.bulletin.toy.security;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthHelper {

    public static final int ACCESS_TOKEN_VALIDITY = 2*60*60; //two hours
    public static final int REFRESH_TOKEN_VALIDITY = 7*24*60*60; //one week
    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";
    public static final String TOKEN_HEADER = "Authorization";

}
