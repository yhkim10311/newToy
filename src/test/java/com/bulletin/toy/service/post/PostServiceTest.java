package com.bulletin.toy.service.post;


import com.bulletin.toy.controller.post.PostDto;
import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.user.UserService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    private String name;

    private String email;

    private String passwd;

    private String content;

    private String title;

    @Before
    public void setUp(){
        name = "John Doe";
        email = "john@gmail.com";
        passwd = "123456789";
        content = "test content";
        title = "test title";
    }

    @Test
    public void test1_Post저장(){
        User user = userService.join(name,email,passwd);

        Post post = Post
                .builder()
                .content(content)
                .title(title)
                .user(user)
                .build();
        PostDto postDto = postService.save(post);

        assertThat(postDto.getContent()).isEqualTo(content);
        assertThat(postDto.getTitle()).isEqualTo(title);
        assertThat(postDto.getUserEmail()).isEqualTo(email);
    }

    @Test
    public void test2_Post조회(){
        PostDto postDto = postService.findById(1L);

        assertThat(postDto.getTitle()).isEqualTo(title);
        assertThat(postDto.getContent()).isEqualTo(content);
        assertThat(postDto.getUserEmail()).isEqualTo(email);
    }

    @Test
    public void test3_Post전체조회(){
        User user = userService.findByEmail(email);

        String newTitle = "New Title";
        String newContent = "New Content";
        Post post = Post
                .builder()
                .content(newContent)
                .title(newTitle)
                .user(user)
                .build();
        PostDto postDto = postService.save(post);
        List<PostDto> postDtoList = postService.findAllDesc();

        assertThat(postDtoList.size()).isEqualTo(2);
        assertThat(postDtoList.get(1).getTitle()).isEqualTo(title);
        assertThat(postDtoList.get(0).getTitle()).isEqualTo(newTitle);
    }

    @Test
    public void test4_Post삭제(){
        PostDto postDto = postService.delete(1L);

        assertThat(postDto.getTitle()).isEqualTo(title);
        assertThat(postDto.getUserEmail()).isEqualTo(email);
    }

}
