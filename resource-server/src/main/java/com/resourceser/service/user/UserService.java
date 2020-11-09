package com.resourceser.service.user;

import com.resourceser.domian.user.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserInfo join(String name, String email, String passwd);

    Optional<UserInfo> findByEmail(String email);

    List<UserDto> findAll();
}
