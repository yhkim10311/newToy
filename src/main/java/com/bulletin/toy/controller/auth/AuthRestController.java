package com.bulletin.toy.controller.auth;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.service.auth.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;

    private final JwtUserDetailService jwtUserDetailService;

    private final JwtTokenService jwtTokenService;

    @PostMapping
    public ApiResult<AuthResult> authentication(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials()));
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(authRequest.getPrincipal());

        return  ApiResult.ok(
                jwtTokenService.getBothTokens(userDetails, response)
        );
    }

    @PostMapping(path="/logout")
    public ApiResult<AuthResult> logout(HttpServletResponse response) {

        return  ApiResult.ok(
                jwtTokenService.removeTokens(response)
        );
    }
}
