package com.bulletin.toy.domain.user;

import com.bulletin.toy.domain.comment.Comment;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String profilePicUrl;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String name, String email, Role role, String profilePicUrl){
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.profilePicUrl = profilePicUrl;
    }

    public User update(String name, String profilePicUrl){
        this.name = name;
        this.profilePicUrl = profilePicUrl;

        return this;
    }

}