package com.yellowmovement.site;


import com.yellowmovement.site.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WelcomeControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homeTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void openCreateAccountPageTest() throws Exception {
        mockMvc.perform(get("/createAccount"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/?performing=createAccount"));
    }

    @Test
    public void LogInTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/?performing=login"));
    }

    @Test
    public void accessdeniedTest() throws Exception {
        mockMvc.perform(get("/access-denied"))
                .andExpect(status().isOk())
                .andExpect(view().name("access_denied"));
    }
//	@Test
//	public void userTest() {
//		WelcomeController w = new WelcomeController();
//		User user = new User();
//		assertThat(w.user()).isEqualTo(user);
//	}

//	@Test
//	public void createNewUserTest() {
//		User user = new User("one","a@mail.com",1,"00","male");
//		User user2 = new User("two","ab@mail.com",1,"00","male");
//		Mockito.when(userService.findUserByEmail(user.getEmail())).thenReturn(user);
//
//		WelcomeController w = new WelcomeController();
//		w.createNewUser(user2, null ,null);
//		assertThat(userService.findUserByEmail(user.getEmail())).isNotEqualTo(user2);
//	}
}
