package com.bulletin.toy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bulletin.toy.controller.ApiResult.OK;

@RestController
@RequestMapping("api/hcheck")
public class HealthCheckRestController {

    @GetMapping
    public ApiResult<Long> healthCheck() { return OK(System.currentTimeMillis());}
}
