package com.bulletin.toy.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken implements Serializable {

    private static final long serialVersionUID = -8489627288483079852L;
    private String userName;
    private String refreshToken;
}
