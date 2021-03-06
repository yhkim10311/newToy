package com.bulletin.toy.controller.post;

import com.bulletin.toy.service.ApiResult;
import com.bulletin.toy.service.auth.CustomUserDetails;
import com.bulletin.toy.service.post.PostDto;
import com.bulletin.toy.service.post.PostRequest;
import com.bulletin.toy.service.post.PostService;
import com.bulletin.toy.service.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping
    public ApiResult<PostDto> posting(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody PostRequest postRequest){
        return ApiResult.ok(
                postService.save(customUserDetails, postRequest)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResult<PostDto> delete(@PathVariable Long id){
        return ApiResult.ok(
                postService.delete(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResult<PostDto> update(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest){
        return ApiResult.ok(
                postService.update(id, postUpdateRequest)
        );
    }

    @GetMapping("/{id}")
    public ApiResult<PostDto> getPost(@PathVariable Long id){
        return ApiResult.ok(
                postService.findById(id)
        );
    }

}
