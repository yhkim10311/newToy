package com.bulletin.toy.controller.user;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bulletin.toy.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("user/join")
    public ApiResult<JoinResult> join(@ModelAttribute JoinRequest joinRequest){
        User user = userService.join(joinRequest.getName(), joinRequest.getPrincipal(), joinRequest.getCredentials());

        return OK(new JoinResult(user));
    }
}
