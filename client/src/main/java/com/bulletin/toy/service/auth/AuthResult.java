package com.bulletin.toy.service.auth;

import com.bulletin.toy.service.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthResult {

    private String accessToken;

    private String refreshToken;

    private UserDto userDto;
}
