package com.resourceser.domian.user;

import com.resourceser.domian.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Entity
public class UserInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String profilePicUrl;

    @Builder
    public UserInfo(String name, String email, String passwd, Role role, String profilePicUrl){
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
        this.profilePicUrl = profilePicUrl;
    }

    public UserInfo update(String name, String passwd, String profilePicUrl){
        this.name = name;
        this.passwd = passwd;
        this.profilePicUrl = profilePicUrl;

        return this;
    }

}