package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.User;

import java.util.Optional;

public interface UserService {

    User join(String name, String email);

    Optional<User> findByEmail(String email);
}
