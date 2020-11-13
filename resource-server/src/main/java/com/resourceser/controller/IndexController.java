package com.resourceser.controller;

import com.resourceser.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;

    @GetMapping("/join")
    public String join(){
        return "joinForm";
    }

    @GetMapping("/")
    public ApiResult<Long> healthCheck() { return ApiResult.ok(System.currentTimeMillis());}
}
