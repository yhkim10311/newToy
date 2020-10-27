package com.bulletin.toy.controller.user;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.security.JwtAuthHelper;
import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.service.user.JoinRequest;
import com.bulletin.toy.service.user.JoinResult;
import com.bulletin.toy.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceImpl userServiceImpl;

    private final JwtAuthHelper jwtAuthHelper;

    @PostMapping("/join")
    public ApiResult<JoinResult> join(@Valid @RequestBody JoinRequest joinRequest){
        User user = userServiceImpl.join(joinRequest.getName(), joinRequest.getPrincipal(), joinRequest.getCredentials());
        String accessToken = jwtAuthHelper.generateAccessToken(new JwtUserDetails(user));
        log.info("Joined"+user.toString());
        return ApiResult.ok(new JoinResult(user, accessToken));
    }
}
