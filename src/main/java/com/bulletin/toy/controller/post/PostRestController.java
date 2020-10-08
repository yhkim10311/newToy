package com.bulletin.toy.controller.post;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.post.PostService;
import com.bulletin.toy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.bulletin.toy.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("post")
    public ApiResult<PostDto> posting(@RequestBody PostRequest postRequest){

        return OK(
                postService.save(postRequest)
        );
    }

    @DeleteMapping("post/{id}")
    public ApiResult<PostDto> delete(@PathVariable Long id){
        return OK(
                postService.delete(id)
        );
    }

    @PutMapping("post/{id}")
    public ApiResult<PostDto> update(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest){
        return OK(
                postService.update(id, postUpdateRequest)
        );
    }
}
