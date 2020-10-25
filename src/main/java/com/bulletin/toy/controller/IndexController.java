package com.bulletin.toy.controller;

import com.bulletin.toy.security.JwtAuthHelper;
import com.bulletin.toy.service.post.PostServiceImpl;
import com.bulletin.toy.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostServiceImpl postServiceImpl;

    private final JwtAuthHelper jwtAuthHelper;

    private final HttpSession httpSession;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request){
        // TODO token expire되면 request에 존재하지 않음... --> Session 사용
        // TODO session 사용하여 리펙토링 하기!!
        Map<String, Object> params = new HashMap<>();
        params.put("posts",postServiceImpl.findAllDesc());

        Object user = httpSession.getAttribute("user");
        if(user!=null) {
            params.put("user",user);
        }

        return new ModelAndView("index", params);
    }

    @GetMapping("/join")
    public String join(){
        return "joinForm";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/post")
    public String post(){
        return "postForm";
    }
}
