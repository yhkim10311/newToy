package com.bulletin.toy.controller.comment;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bulletin.toy.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("post/{postId}/comment")
    public ApiResult<CommentDto> commenting(@ModelAttribute CommentRequest commentRequest,
                                            @PathVariable Long postId){
        return OK(
                commentService.save(commentRequest, postId)
        );
    }

    @GetMapping("post/{postId}/comment")
    public ApiResult<List<CommentDto>> comments(@PathVariable Long postId){
        return OK(
                commentService.findAllDesc(postId)
        );
    }

}
