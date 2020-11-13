package com.bulletin.toy.client;

import com.bulletin.toy.service.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="resourceServer", url = "${feign.resource.server-url}")
public interface ResourceServerClient {

    @GetMapping("/api/auth/resource")
    ApiResult<String> getResource(@RequestHeader("Authorization") String header);
}
