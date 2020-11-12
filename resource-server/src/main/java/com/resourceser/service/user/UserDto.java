package com.resourceser.service.user;

import com.resourceser.domian.user.Role;
import com.resourceser.domian.user.UserInfo;
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

    public UserDto(UserInfo userInfo){
        copyProperties(userInfo, this);
    }

}
