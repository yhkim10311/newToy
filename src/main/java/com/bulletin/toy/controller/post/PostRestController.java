package com.bulletin.toy.controller.post;

import com.bulletin.toy.controller.ApiResult;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.post.PostService;
import com.bulletin.toy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bulletin.toy.controller.ApiResult.OK;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    private final UserService userService;

    @PostMapping("post")
    public ApiResult<PostDto> posting(@RequestBody PostRequest postRequest){
        // TODO 임시처리... JWT 개발 후 RequestBody(클래스 필드값) 수정 예정
        User user = userService.findByEmail(postRequest.getEmail());

        return OK(
                postService.save(postRequest.toEntity(user))
        );
    }

    @DeleteMapping("post/{id}")
    public ApiResult<PostDto> delete(@PathVariable Long id){
        return OK(
                postService.delete(id)
        );
    }
}
