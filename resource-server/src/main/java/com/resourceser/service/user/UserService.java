package com.resourceser.service.user;

import com.resourceser.domian.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User join(String name, String email, String passwd);

    Optional<User> findByEmail(String email);

    List<UserDto> findAll();
}
