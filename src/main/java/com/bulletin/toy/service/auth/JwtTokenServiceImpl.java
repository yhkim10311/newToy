package com.bulletin.toy.service.auth;

import com.bulletin.toy.domain.auth.RefreshToken;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.security.JwtAuthHelper;
import com.bulletin.toy.service.user.UserDto;
import com.bulletin.toy.util.CookieUtil;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtUserDetailService jwtUserDetailService;

    private final JwtAuthHelper jwtAuthHelper;

    private final RedisTemplate<String, Object> redisTemplate;

    private final HttpSession httpSession;

    @Override
    public AuthResult getBothTokens(UserDetails userDetails, HttpServletResponse response) {
        String accessToken = jwtAuthHelper.generateAccessToken(userDetails);
        String refreshToken = jwtAuthHelper.generateRefreshToken(userDetails);

        RefreshToken refreshTokenToSave = new RefreshToken(userDetails.getUsername(),refreshToken);
        redisTemplate.opsForValue().set(userDetails.getUsername(),refreshTokenToSave);

        Cookie accessCookie = CookieUtil.createCookie(accessToken, "accessToken");
        Cookie refreshCookie = CookieUtil.createCookie(refreshToken, "refreshToken");

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return new AuthResult(accessToken, refreshToken, new UserDto(((JwtUserDetails) userDetails).getUser()));
    }

    @Override
    public AuthResult removeTokens(HttpServletResponse response) {
        Cookie accessCookie = CookieUtil.removeCookie(JwtAuthHelper.ACCESS_TOKEN_NAME);
        Cookie refreshCookie = CookieUtil.removeCookie(JwtAuthHelper.REFRESH_TOKEN_NAME);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        Object user = httpSession.getAttribute("user");
        if(user != null) redisTemplate.delete(((User)user).getEmail());

        return new AuthResult(null,null,null);
    }
}
