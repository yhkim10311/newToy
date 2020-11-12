package com.resourceser.controller.auth;

import com.resourceser.controller.ApiResult;
import com.resourceser.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthService authService;

    @GetMapping("/resource")
    public ApiResult<String> authenticate(@RequestHeader(value="Authorization") String token){
        return ApiResult.ok(
                authService.authenticateGivenToken(token)
        );
    }
}
