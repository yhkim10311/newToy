package com.bulletin.toy.service.post;

import com.bulletin.toy.controller.post.PostDto;
import com.bulletin.toy.controller.post.PostRequest;
import com.bulletin.toy.controller.post.PostUpdateRequest;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.post.PostRepository;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public PostDto save(PostRequest postRequest){

        User user = userRepository.findByEmail(postRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        return new PostDto(postRepository.save(postRequest.toEntity(user)));
    }

    @Transactional
    public Post findById(Long id){
        return findThePost(id);
    }

    @Transactional
    public PostDto delete(Long id){
        Post post = findThePost(id);
        postRepository.delete(post);
        return new PostDto(
                post
        );
    }

    @Transactional
    public PostDto update(Long id, PostUpdateRequest postUpdateRequest){
        return new PostDto(
                findThePost(id)
                        .update(postUpdateRequest.getTitle(),postUpdateRequest.getContent())
        );
    }

    @Transactional
    public List<PostDto> findAllDesc(){
        return postRepository
                .findAll(new Sort(Sort.Direction.DESC, "id"))
                .stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }

    private Post findThePost(Long id){
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 습니다."));
    }
}
