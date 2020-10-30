package com.bulletin.toy.controller;

import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.service.comment.CommentService;
import com.bulletin.toy.service.post.PostDto;
import com.bulletin.toy.service.post.PostService;
import com.bulletin.toy.service.post.PostServiceImpl;
import com.bulletin.toy.service.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;

    private final SessionRepository sessionRepository;

    private final String sessionName = "user";

    private final CommentService commentService;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request){
        Map<String, Object> params = new HashMap<>();
        params.put("posts",postService.findAllDesc());
        Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Session session;
        session = sessionRepository.findById(sessionName);

        if(session!=null) {
            params.put(sessionName,new UserDto(((JwtUserDetails) user).getUser()));
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

    @GetMapping("/post/{postId}")
    public ModelAndView getPost(@PathVariable Long postId){

        Map<String, Object> params = new HashMap<>();
        params.put("post",postService.findById(postId));

        params.put("comments",commentService.findAllDesc(postId));

        return new ModelAndView("thePost", params);
    }

}
