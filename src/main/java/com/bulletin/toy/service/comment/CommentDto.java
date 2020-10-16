package com.bulletin.toy.service.comment;

import com.bulletin.toy.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class CommentDto {

    private Long id;

    private String content;

    private String userEmail;

    private Long postId;

    private Optional<Long> commentId;

    public CommentDto(Comment comment){
        copyProperties(comment, this);

        this.userEmail = comment.getUser().getEmail();
        this.postId = comment.getPost().getId();
        this.commentId = comment.getComment().map(Comment::getId);
    }
}
