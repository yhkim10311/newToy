package com.authser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @Value("${auth.client-id}")
    private String clinetId;

    @Value("${auth.redirect-url}")
    private String redirectUrl;

    @GetMapping("/authorize")
    public ModelAndView login(@RequestParam(value = "client_id") String clientId,
                              @RequestParam(value = "redirect_url") String redirectUrl) {
        if(!this.clinetId.equals(clientId) || !this.redirectUrl.equals(redirectUrl)) {
            throw new IllegalArgumentException("No such redirect-url with the provided client id");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("redirect",redirectUrl);

        return new ModelAndView("loginForm", params);
    }


}
