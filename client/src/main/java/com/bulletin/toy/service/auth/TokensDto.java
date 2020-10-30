package com.bulletin.toy.service.auth;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokensDto {

    private String accessToken;

    private String refreshToken;
}
