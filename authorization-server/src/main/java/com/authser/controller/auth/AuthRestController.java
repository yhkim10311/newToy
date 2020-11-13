package com.authser.controller.auth;

import com.authser.controller.ApiResult;
import com.authser.domian.user.Role;
import com.authser.domian.user.UserInfo;
import com.authser.jwt.JwtTokenHelper;
import com.authser.service.auth.AuthRequest;
import com.authser.service.auth.AuthResult;
import com.authser.service.auth.CustomUserDetails;
import com.authser.service.user.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenHelper jwtTokenHelper;

    @Value("${auth.authCode}")
    private String authCode;

    @Value("${auth.redirect-url}")
    private String redirectUrl;

    @Value("${auth.client-secret}")
    private String clientSecret;

    @Value("${auth.client-id}")
    private String clinetId;

    @PostMapping
    public ApiResult<AuthResult> authentication(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Authorization of Auth Server start!!!");
        
        return  ApiResult.ok(
                new AuthResult(authCode,redirectUrl,new UserDto(((CustomUserDetails)authentication.getPrincipal()).getUserInfo()))
        );
    }

    @GetMapping("/token")
    public ApiResult<String> getAccessToken(@RequestParam(value = "client_id") String clientId,
                                            @RequestParam(value = "redirect_url") String redirectUrl,
                                            @RequestParam(value = "auth_code") String authCode,
                                            @RequestParam(value = "user_id") String userId,
                                            @RequestHeader(value="Authorization") String clientSecret){
        if(!this.clinetId.equals(clientId) || !this.redirectUrl.equals(redirectUrl) || !this.authCode.equals(authCode)
                || !this.clientSecret.equals(clientSecret)) {
            throw new IllegalArgumentException("Wrong client server information");
        }
        return ApiResult.ok(
                jwtTokenHelper.generateAccessToken(new CustomUserDetails(UserInfo.builder().email(userId).role(Role.USER).build()))
        );
    }
}
