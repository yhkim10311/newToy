package com.bulletin.toy.controller.post;

import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostRequest {

    private String title;

    private String content;

    private String email;

    public Post toEntity(User user){
        return Post
                .builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
