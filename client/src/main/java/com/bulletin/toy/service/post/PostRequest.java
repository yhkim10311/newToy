package com.bulletin.toy.service.post;

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

    @Builder
    public PostRequest(String title, String content){
        this.title = title;
        this.content = content;
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
