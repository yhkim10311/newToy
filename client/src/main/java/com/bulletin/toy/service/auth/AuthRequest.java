package com.bulletin.toy.service.auth;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String principal;

    private String credentials;

}
