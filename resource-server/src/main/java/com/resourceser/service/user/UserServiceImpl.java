package com.resourceser.service.user;

import com.resourceser.domian.user.Role;
import com.resourceser.domian.user.User;
import com.resourceser.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User join(String name, String email, String passwd){

        checkArgument(passwd.length()>=8, "password length must be greater than 7");

        User user = User.builder()
                .name(name)
                .email(email)
                .passwd(passwordEncoder.encode(passwd))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }
}
