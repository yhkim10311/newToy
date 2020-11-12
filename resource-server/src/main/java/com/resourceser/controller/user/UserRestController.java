package com.resourceser.controller.user;

import com.resourceser.controller.ApiResult;
import com.resourceser.domian.user.UserInfo;
import com.resourceser.service.user.JoinRequest;
import com.resourceser.service.user.JoinResult;
import com.resourceser.service.user.UserDto;
import com.resourceser.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResult<JoinResult> join(@Valid @RequestBody JoinRequest joinRequest){
        UserInfo userInfo = userService.join(joinRequest.getName(), joinRequest.getPrincipal(), joinRequest.getCredentials());
        log.info("Joined"+ userInfo.toString());
        return ApiResult.ok(new JoinResult(new UserDto(userInfo)));
    }

    @GetMapping("/all")
    public ApiResult<List<UserDto>> findAllUsers(){
        return ApiResult.ok(userService.findAll());
    }
}
