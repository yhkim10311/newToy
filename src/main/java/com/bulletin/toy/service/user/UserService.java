package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(String name, String email, String passwd){

        checkArgument(passwd.length()>8, "password length must be greater than 8");

        User user = User.builder()
                .name(name)
                .email(email)
                .passwd(passwordEncoder.encode(passwd))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public User findByEmail(String email){

        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }
}
