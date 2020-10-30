package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.User;

public interface UserService {

    User join(String name, String email, String passwd);

    User findByEmail(String email);
}
