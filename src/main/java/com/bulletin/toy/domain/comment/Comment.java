package com.bulletin.toy.domain.comment;

import com.bulletin.toy.domain.BaseTimeEntity;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id") // TODO 명시적으로 칼럼 이름
    private Comment comment;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();

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
