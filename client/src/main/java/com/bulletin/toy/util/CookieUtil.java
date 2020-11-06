package com.bulletin.toy.util;

import com.bulletin.toy.security.JwtAuthHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CookieUtil {

    public static Cookie createCookie(String token, String type){
        Cookie cookie = new Cookie(type, token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        cookie.setMaxAge(-1);

        return cookie;
    }

    public static Cookie removeCookie(String type){
        Cookie cookie = new Cookie(type, null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        return cookie;
    }

    public static Optional<Cookie> getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

}
