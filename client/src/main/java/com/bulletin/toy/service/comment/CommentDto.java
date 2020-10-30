package com.bulletin.toy.service.comment;

import com.bulletin.toy.domain.comment.Comment;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class CommentDto implements Comparable<CommentDto>{

    private Long id;

    private String content;

    private String userEmail;

    private Long postId;

    private Optional<Long> commentId;

    private LocalDateTime createdDate;

    private Long depth;

    private List<CommentDto> comments;

    public CommentDto(Comment comment){
        copyProperties(comment, this);

        this.userEmail = comment.getUser().getEmail();
        this.postId = comment.getPost().getId();
        this.commentId = comment.getComment().map(Comment::getId);
        this.depth = comment.getDepth();
        this.comments = comment.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        Collections.sort(comments);
    }

    @Override
    public int compareTo(CommentDto commentDto) {
        if(this.createdDate.isAfter(commentDto.createdDate)){
            return 1;
        }else{
            return -1;
        }
    }
}
