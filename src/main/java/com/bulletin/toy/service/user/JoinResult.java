package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.User;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class JoinResult {

    private final User user;

    private final String accessToken;

}
