package com.bulletin.toy.client;

import com.bulletin.toy.service.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="authServer", url = "${auth.server-url}")
public interface AuthServerClient {

    @GetMapping("/api/auth/token")
    ApiResult<String> getToken(@RequestParam(value = "client_id") String clientId,
                               @RequestParam(value = "redirect_url") String redirectUrl,
                               @RequestParam(value = "auth_code") String authCode,
                               @RequestParam(value = "user_id") String userId,
                               @RequestHeader("Authorization") String header);
}
