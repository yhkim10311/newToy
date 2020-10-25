package com.bulletin.toy.controller.comment;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.service.comment.CommentDto;
import com.bulletin.toy.service.comment.CommentRequest;
import com.bulletin.toy.service.comment.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentServiceImpl commentServiceImpl;

    @PostMapping("/post/{postId}/comment")
    public ApiResult<CommentDto> commenting(@AuthenticationPrincipal JwtUserDetails jwtUserDetails,
                                            @ModelAttribute CommentRequest commentRequest,
                                            @PathVariable Long postId){
        return ApiResult.ok(
                commentServiceImpl.save(jwtUserDetails, commentRequest, postId)
        );
    }

    @GetMapping("/post/{postId}/comment")
    public ApiResult<List<CommentDto>> comments(@PathVariable Long postId){
        return ApiResult.ok(
                commentServiceImpl.findAllDesc(postId)
        );
    }
/*
    @PutMapping("post/{postId}/comment/{commentId}")
    public ApiResult<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId){

    }
*/
}
