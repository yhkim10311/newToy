package com.bulletin.toy.controller.user;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserServiceImpl userService;

    private String name;

    private String email;

    private String passwd;

    @Before
    public void setUp(){
        name = "John Doe";
        email = "john@gmail.com";
        passwd = "123456789";

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void test1_회원가입() throws Exception{
        // TODO JSON 확인
        User user = User.builder().email(email).name(name).passwd(passwd).role(Role.USER).build();

        given(userService.join(name,email,passwd)).willReturn(user);


        mockMvc.perform(post("/api/user/join")
                .content("{\"principal\":\""+email+"\", \"credentials\":\""+passwd+"\", \"name\":\""+name+"\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(email)));

    }

}
