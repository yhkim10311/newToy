package com.bulletin.toy.controller.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JoinRequest {

    private String name;

    private String principal;

    private String credentials;
}
