package com.bulletin.toy.domain.comment;

import com.bulletin.toy.domain.BaseTimeEntity;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn
    private Comment comment;

    @Builder
    public Comment(String content, User user, Post post, Comment comment){
        checkArgument(comment == null || comment.getComment().orElse(null)==null , "Only 2 depth of comment allowed");

        this.content = content;
        this.user = user;
        this.post = post;
        this.comment = comment;
    }

    public Optional<Comment> getComment(){
        return Optional.ofNullable(comment);
    }
}
