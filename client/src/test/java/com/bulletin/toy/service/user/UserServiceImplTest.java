package com.bulletin.toy.service.user;

import com.bulletin.toy.domain.user.User;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {
/*
    @Autowired
    private UserServiceImpl userServiceImpl;

    private String name;

    private String email;

    private String passwd;

    @Before
    public void setUp(){
        name = "John Doe";
        email = "john@gmail.com";
        passwd = "123456789";
    }


    @Test
    public void test1_회원가입(){
        User user = userServiceImpl.join(name,email);

        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void test2_이메일로_회원조회(){
        User user = userServiceImpl.findByEmail(email).orElse(null);

        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
    }
*/

}
