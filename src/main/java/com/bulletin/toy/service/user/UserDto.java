package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private String profilePicUrl;

    public UserDto(User user){
        copyProperties(user, this);
    }

}
