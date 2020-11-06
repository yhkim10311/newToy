package com.resourceser.service.auth;

import com.resourceser.domian.user.User;
import com.resourceser.domian.user.UserRepository;
import com.resourceser.jwt.JwtTokenHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final JwtTokenHelper jwtTokenHelper;

    @Override
    public String authenticateGivenToken(String token) throws IllegalArgumentException{

        String userId = jwtTokenHelper.getUsernameFromToken(validateToken(token));

        User user = userRepository.findByEmail(userId).orElseThrow(() -> new IllegalArgumentException("There is no user with such email"));

        return user.getEmail();
    }

    private String validateToken(String token){
        String[] parts = token.split(" ");

        if(!"Bearer".equals(parts[0])){
            throw new IllegalArgumentException("Token format is invalid");
        }
        return parts[1];
    }
}
