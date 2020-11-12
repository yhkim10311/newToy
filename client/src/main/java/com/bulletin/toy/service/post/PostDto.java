package com.bulletin.toy.service.post;

import com.bulletin.toy.domain.comment.Comment;
import com.bulletin.toy.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@NoArgsConstructor
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
        BeanUtils.copyProperties(post, this);

        userEmail = post.getUser().getEmail();
    }
}
