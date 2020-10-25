package com.bulletin.toy.service.post;

import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.service.auth.JwtUserDetails;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

public interface PostService {

    PostDto save(JwtUserDetails jwtUserDetails, PostRequest postRequest);

    PostDto delete(Long id);

    PostDto update(Long id, PostUpdateRequest postUpdateRequest);

    List<PostDto> findAllDesc();

}
