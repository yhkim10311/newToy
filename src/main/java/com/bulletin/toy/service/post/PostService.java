package com.bulletin.toy.service.post;

import com.bulletin.toy.controller.post.PostDto;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.post.PostRepository;
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

    @Transactional
    public PostDto save(Post post){
        return new PostDto(postRepository.save(post));
    }

    @Transactional
    public PostDto findById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 않습니다."));
        return new PostDto(post);
    }

    @Transactional
    public PostDto delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 존재하지 습니다."));
        postRepository.delete(post);
        return new PostDto(post);
    }

    @Transactional
    public List<PostDto> findAllDesc(){
        return postRepository
                .findAll(new Sort(Sort.Direction.DESC, "id"))
                .stream()
                .map(PostDto::new)
                .collect(Collectors.toList());
    }
}
