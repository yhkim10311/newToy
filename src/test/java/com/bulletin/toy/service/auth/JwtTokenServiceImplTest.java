package com.bulletin.toy.service.auth;

import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JwtTokenServiceImplTest {

    @Autowired
    JwtTokenServiceImpl jwtTokenServiceImpl;

    @Autowired
    JwtUserDetailService jwtUserDetailService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    private String name;

    private String email;

    private String passwd;

    private HttpServletResponse httpServletResponse;

    @Before
    public void setUp(){
        name = "John Doe";
        email = "john@gmail.com";
        passwd = "123456789";
        httpServletResponse = new MockHttpServletResponse();
    }

    @Test
    public void test1_토큰발급(){
        User user = userServiceImpl.join(name,email,passwd);

        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(email);

        AuthResult authResult = jwtTokenServiceImpl.getBothTokens(userDetails, httpServletResponse);

        assertThat(authResult.getUserDto().getEmail()).isEqualTo(email);
        assertThat(authResult.getAccessToken()).isGreaterThan(" ");
        assertThat(authResult.getRefreshToken()).isGreaterThan(" ");
    }

    @Test
    public void test2_토큰삭제(){
        AuthResult authResult = jwtTokenServiceImpl.removeTokens(httpServletResponse);
        assertThat(authResult.getAccessToken()).isNull();
    }
}
