package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User join(String name, String email){

        User user = User.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
