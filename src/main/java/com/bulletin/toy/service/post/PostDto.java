package com.bulletin.toy.service.post;

import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class PostDto {

    private Long id;

    private String title;

    private String content;

    private String userEmail;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    public PostDto(Post post){
        copyProperties(post, this);

        userEmail = post.getUser().getEmail();
    }
}