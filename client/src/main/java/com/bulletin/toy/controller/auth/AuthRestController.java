package com.bulletin.toy.controller.auth;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.service.auth.AuthResult;
import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.service.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bulletin.toy.service.auth.AuthRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;

    private final HttpSession httpSession;

    @PostMapping
    public ApiResult<AuthResult> authentication(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        // TODO 로그인 누르면, Auth server 화면으로 redirect..
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute("user",authentication.getName());

        return  ApiResult.ok(
                new AuthResult(null,null,new UserDto(((JwtUserDetails)authentication.getPrincipal()).getUser()))
        );
    }

}
