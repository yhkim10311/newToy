package com.bulletin.toy.service.comment;

import com.bulletin.toy.domain.comment.Comment;
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
public class CommentRequest {

    private String content;

    private Long commentId;

    public Comment toEntity(User user, Comment comment, Post post){
        return Comment
                .builder()
                .content(content)
                .user(user)
                .comment(comment)
                .post(post)
                .build();
    }
}
