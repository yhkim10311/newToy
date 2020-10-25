package com.bulletin.toy.service.post;


import com.bulletin.toy.domain.post.Post;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.auth.JwtUserDetails;
import com.bulletin.toy.service.user.UserServiceImpl;
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
public class PostServiceImplTest {

    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

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
        User user = userServiceImpl.join(name,email,passwd);

        JwtUserDetails jwtUserDetails = new JwtUserDetails(user);
        PostRequest postRequest  = PostRequest
                .builder()
                .content(content)
                .title(title)
                .build();
        PostDto postDto = postServiceImpl.save(jwtUserDetails,postRequest);

        assertThat(postDto.getContent()).isEqualTo(content);
        assertThat(postDto.getTitle()).isEqualTo(title);
        assertThat(postDto.getUserEmail()).isEqualTo(email);
    }

    @Test
    public void test2_Post조회(){
        Post post = postServiceImpl.findById(1L);

        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getUser().getEmail()).isEqualTo(email);
    }

    @Test
    public void test3_Post전체조회(){
        User user = userServiceImpl.findByEmail(email);
        JwtUserDetails jwtUserDetails = new JwtUserDetails(user);
        String newTitle = "New Title";
        String newContent = "New Content";

        PostRequest postRequest  = PostRequest
                .builder()
                .content(newContent)
                .title(newTitle)
                .build();
        PostDto postDto = postServiceImpl.save(jwtUserDetails, postRequest);
        List<PostDto> postDtoList = postServiceImpl.findAllDesc();

        assertThat(postDtoList.size()).isEqualTo(2);
        assertThat(postDtoList.get(1).getTitle()).isEqualTo(title);
        assertThat(postDtoList.get(0).getTitle()).isEqualTo(newTitle);
    }

    @Test
    public void test4_Post삭제(){
        PostDto postDto = postServiceImpl.delete(1L);

        assertThat(postDto.getTitle()).isEqualTo(title);
        assertThat(postDto.getUserEmail()).isEqualTo(email);
    }

}
