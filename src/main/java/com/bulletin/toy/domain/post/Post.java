package com.bulletin.toy.domain.post;

import com.bulletin.toy.domain.BaseTimeEntity;
import com.bulletin.toy.domain.comment.Comment;
import com.bulletin.toy.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String title, String content, User user){
        checkNotNull(title,"제목을 입력하세요");
        checkNotNull(content,"내용을 입력하세요");
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Post update(String title, String content){
        this.title = title;
        this.content = content;

        return this;
    }
}
