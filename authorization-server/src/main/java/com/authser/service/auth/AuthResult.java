package com.authser.service.auth;

import com.authser.service.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthResult {

    private String authCode;

    private String redirectUrl;

    private UserDto userDto;
}
