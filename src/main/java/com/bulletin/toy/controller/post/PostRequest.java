package com.bulletin.toy.controller.post;

import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostRequest {

    private String title;

    private String content;

    // TODO 인증처리 추가 후 삭제
    private String email;

    @Builder
    public PostRequest(String title, String content, String email){
        this.title = title;
        this.content = content;
        this.email = email;
    }

    public Post toEntity(User user){
        return Post
                .builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
