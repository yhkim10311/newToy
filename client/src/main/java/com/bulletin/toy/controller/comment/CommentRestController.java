package com.bulletin.toy.controller.comment;

import com.bulletin.toy.service.ApiResult;
import com.bulletin.toy.service.auth.CustomUserDetails;
import com.bulletin.toy.service.comment.CommentDto;
import com.bulletin.toy.service.comment.CommentRequest;
import com.bulletin.toy.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ApiResult<CommentDto> commenting(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                            @RequestBody CommentRequest commentRequest,
                                            @PathVariable Long postId){
        return ApiResult.ok(
                commentService.save(customUserDetails, commentRequest, postId)
        );
    }

    @GetMapping("/post/{postId}/comment")
    public ApiResult<List<CommentDto>> comments(@PathVariable Long postId){
        return ApiResult.ok(
                commentService.findAllDesc(postId)
        );
    }

    @PostMapping("post/{postId}/comment/{commentId}")
    public ApiResult<CommentDto> updateComment(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequest commentRequest){
        commentRequest.setCommentId(commentId);
        return ApiResult.ok(
                commentService.save(customUserDetails, commentRequest, postId)
        );
    }

}
