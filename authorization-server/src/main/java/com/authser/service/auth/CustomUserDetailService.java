package com.authser.service.auth;

import com.authser.domian.user.UserInfo;
import com.authser.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannnot find the user:"+username));

        return new CustomUserDetails(userInfo);
    }
}
