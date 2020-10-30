package com.bulletin.toy.service.comment;

import com.bulletin.toy.domain.comment.Comment;
import com.bulletin.toy.domain.comment.CommentRepository;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.post.PostRepository;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.domain.user.UserRepository;
import com.bulletin.toy.service.auth.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public CommentDto save(JwtUserDetails jwtUserDetails, CommentRequest commentRequest, Long postId) {
        User user = userRepository.findByEmail(jwtUserDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        Comment comment = commentRequest.getCommentId() != null ?
                findById(commentRequest.getCommentId())
                        .filter(comm -> comm.getPost().getId().equals(postId))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."))
                : null;
        return new CommentDto(commentRepository.save(commentRequest.toEntity(user, comment, post, commentRequest.getContent())));
    }

    @Override
    public Optional<Comment> findById(Long id){
        return commentRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> findAllDesc(Long postId){
        return commentRepository.findAllDesc(postId)
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
