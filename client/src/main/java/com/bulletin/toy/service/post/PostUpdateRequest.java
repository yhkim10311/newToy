package com.bulletin.toy.service.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostUpdateRequest {

    private String title;

    private String content;

}
