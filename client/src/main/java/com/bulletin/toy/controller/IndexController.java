package com.bulletin.toy.controller;

import com.bulletin.toy.service.comment.CommentService;
import com.bulletin.toy.service.post.PostDto;
import com.bulletin.toy.service.post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    private final HttpSession httpSession;

    private final String sessionName = "user";

    private final CommentService commentService;

    @Value("${oauth.authserver}")
    private String authServerUrl;

    @Value("${oauth.resourceserver}")
    private String resourceServerUrl;

    @Value("${auth.client-id}")
    private String cliendId;

    @Value("${auth.client-secret}")
    private String clientSecret;

    @Value("${auth.redirect-url}")
    private String redirectUrl;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> params = new ModelMap();
        params.put("posts",postService.findAllDesc());
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = (String) httpSession.getAttribute(sessionName);

        if(userId!=null) {
            params.put(sessionName,userId);
        }

        params.put("authServerUrl",authServerUrl);
        params.put("resourceServerUrl",resourceServerUrl);
        params.put("cliendId",cliendId);
        params.put("clientSecret",clientSecret);
        params.put("redirectUrl",redirectUrl);


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

    @GetMapping("/post/{postId}")
    public ModelAndView getPost(@PathVariable Long postId){

        Map<String, Object> params = new ModelMap();
        PostDto post = postService.findById(postId);
        params.put("post", post);

        params.put("comments",commentService.findAllDesc(postId));

        String userId = (String) httpSession.getAttribute(sessionName);

        if(userId!=null && post.getUserEmail().equals(userId)) {
            return new ModelAndView("thePostAdmin", params);
        }

        return new ModelAndView("thePost", params);
    }

    @GetMapping("/accessdenied")
    public String accessdenied(){ return "accessdenied";}
}
