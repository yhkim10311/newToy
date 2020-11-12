package com.bulletin.toy.controller.user;

import com.bulletin.toy.domain.user.Role;
import com.bulletin.toy.domain.user.User;
import com.bulletin.toy.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;

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
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void test1_회원가입() throws Exception{
        User user = User.builder().email(email).name(name).role(Role.USER).build();

        BDDMockito.given(userService.join(name,email)).willReturn(user);


        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/join")
                .content("{\"principal\":\""+email+"\", \"credentials\":\""+passwd+"\", \"name\":\""+name+"\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString(email)));
    }

}
