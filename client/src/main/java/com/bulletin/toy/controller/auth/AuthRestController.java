package com.bulletin.toy.controller.auth;

import com.bulletin.toy.security.JwtAuthHelper;
import com.bulletin.toy.service.auth.AuthService;
import com.bulletin.toy.service.user.UserService;
import com.bulletin.toy.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final HttpSession httpSession;

    private final AuthService authService;

    private final UserService userService;

    @GetMapping("/callback")
    public void callBack(@RequestParam(value = "auth_code") String authCode,
                         @RequestParam(value = "user_id") String userId,
                         HttpServletResponse response) throws IOException {
        log.debug("Authorization redirect successful!");
        String token = authService.getResourceWithToken(authCode, userId);
        userService.findByEmail(userId).orElseGet(() -> userService.join("name",userId));

        httpSession.setAttribute("user",userId);
        response.addCookie(CookieUtil.createCookie(token,JwtAuthHelper.ACCESS_TOKEN_NAME));


        log.debug("Start redirection here : {}",response);
        response.sendRedirect("/");
    }
}
