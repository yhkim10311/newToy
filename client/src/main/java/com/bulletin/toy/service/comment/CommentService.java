package com.bulletin.toy.service.comment;

import com.bulletin.toy.domain.comment.Comment;
import com.bulletin.toy.service.auth.JwtUserDetails;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    CommentDto save(JwtUserDetails jwtUserDetails, CommentRequest commentRequest, Long postId);

    Optional<Comment> findById(Long id);

    List<CommentDto> findAllDesc(Long postId);
}
