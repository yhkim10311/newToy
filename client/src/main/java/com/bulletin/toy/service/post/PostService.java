package com.bulletin.toy.service.post;

import com.bulletin.toy.service.auth.CustomUserDetails;

import java.util.List;

public interface PostService {

    PostDto save(CustomUserDetails customUserDetails, PostRequest postRequest);

    PostDto delete(Long id);

    PostDto update(Long id, PostUpdateRequest postUpdateRequest);

    List<PostDto> findAllDesc();

    PostDto findById(Long id);
}
