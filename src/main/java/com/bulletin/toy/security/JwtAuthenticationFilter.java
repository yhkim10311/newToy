package com.bulletin.toy.security;

import com.bulletin.toy.domain.auth.RefreshToken;
import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.auth.JwtUserDetailService;
import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.util.CookieUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtAuthHelper jwtAuthHelper;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HttpSession httpSession;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional<Cookie> accessCookie = CookieUtil.getCookie(request, JwtAuthHelper.ACCESS_TOKEN_NAME);
        Optional<Cookie> refreshCookie = CookieUtil.getCookie(request, JwtAuthHelper.REFRESH_TOKEN_NAME);
        // TODO 메소드 분리.. refactor: command shift a
        if (accessCookie.isPresent() && !jwtAuthHelper.isTokenExpired(accessCookie.get().getValue())) {  // when accessToken present and not expired
            String userName = findUserNameFromCookie(accessCookie);

            String accessToken = accessCookie.get().getValue();
            setAuthorities(accessToken, userName);

        } else {  // when access token is not valid
            if (refreshCookie.isPresent() && !jwtAuthHelper.isTokenExpired(refreshCookie.get().getValue())) {  // when refresh token present and not expired
                // create new access token...
                String userName = findUserNameFromCookie(refreshCookie);

                if (userName != null) {
                    RefreshToken refreshTokenSaved = (RefreshToken) redisTemplate.opsForValue().get(userName);

                    if (refreshTokenSaved.getRefreshToken().equals(refreshCookie.get().getValue())) {
                        try {
                            String accessToken = jwtAuthHelper.generateAccessToken(jwtUserDetailService.loadUserByUsername(userName));
                            setAuthorities(accessToken, userName);
                            response.addCookie(CookieUtil.createCookie(accessToken, JwtAuthHelper.ACCESS_TOKEN_NAME));
                        } catch (IllegalArgumentException e) {
                            notAuthenticated(response,userName);
                        }
                    }
                } else {
                    notAuthenticated(response,userName);
                }
            } else {
                notAuthenticated(response,null);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void notAuthenticated(HttpServletResponse response, String userName){
        response.addCookie(CookieUtil.removeCookie(JwtAuthHelper.REFRESH_TOKEN_NAME));
        httpSession.setAttribute("user",null);
        if(userName!=null) redisTemplate.delete(userName);
    }

    private void setAuthorities(String token, String userName) {
        if (userName == null) {
            log.info("Token expired");
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            List<LinkedHashMap<String, String>> roles = jwtAuthHelper.getClaims(token).get("role", List.class);
            GrantedAuthority authority = null;
            Role userRole = null;
            for (LinkedHashMap<String, String> role : roles) {
                authority = new SimpleGrantedAuthority(role.get("authority"));
                authorities.add(authority);
                if (Role.USER.getKey().equals(authority.getAuthority()))
                    userRole = Role.USER;
            }
            User user = User.builder().email(userName).name("name").passwd("passwd").role(userRole).build();
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(new JwtUserDetails(user), null, authorities);
            httpSession.setAttribute("user",user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private String findUserNameFromCookie(Optional<Cookie> cookie) {
        try {
            return jwtAuthHelper.getUsernameFromToken(cookie.get().getValue());
        } catch (IllegalArgumentException e) {
            log.warn("Unable to get token", e);
        } catch (ExpiredJwtException e) {
            log.info("Token expired", e);
        }
        return null;
    }
}
