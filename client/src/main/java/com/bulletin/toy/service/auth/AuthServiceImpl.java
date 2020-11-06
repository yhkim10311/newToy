package com.bulletin.toy.service.auth;

import com.bulletin.toy.client.AuthServerClient;
import com.bulletin.toy.client.ResourceServerClient;
import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthServerClient authServerClient;

    private final ResourceServerClient resourceServerClient;

    @Value("${auth.client-id}")
    private String cliendId;

    @Value("${auth.client-secret}")
    private String clientSecret;

    @Value("${auth.redirect-url}")
    private String redirectUrl;

    @Override
    public String getResourceWithToken(String authCode, String userId) {
        ApiResult<String> result = authServerClient.getToken(cliendId,redirectUrl,authCode,userId,clientSecret);
        if(!result.isSuccess()){
            throw new IllegalArgumentException("Could not generate access token");
        }
        String token = "Bearer "+result.getResponse();

        String receivedId = resourceServerClient.getResource(token).getResponse();
        if(!userId.equals(receivedId)) {
            throw new IllegalArgumentException("User Id does not match");
        }
        setAuthentication(userId);
        return token;
    }

    private void setAuthentication(String userId) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
        User user = User.builder().email(userId).name("name").role(Role.USER).build();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
