package com.resourceser.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheckRestController {

    @GetMapping("/")
    public ApiResult<Long> healthCheck() { return ApiResult.ok(System.currentTimeMillis());}
}
