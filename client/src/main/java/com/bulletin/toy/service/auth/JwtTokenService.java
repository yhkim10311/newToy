package com.bulletin.toy.service.auth;

import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletResponse;

public interface JwtTokenService {

    AuthResult getBothTokens(UserDetails userDetails, HttpServletResponse response);

//    AuthResult refreshToken(TokensDto tokensDto, HttpServletResponse response) throws NotFoundException;

    AuthResult removeTokens(HttpServletResponse response);
}
