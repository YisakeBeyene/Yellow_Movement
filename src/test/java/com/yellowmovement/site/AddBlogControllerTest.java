package com.yellowmovement.site;

import com.yellowmovement.site.configurations.PasswordEncoderConfig;
import com.yellowmovement.site.controllers.AddBlogController;
import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(AddBlogController.class)
@ContextConfiguration(classes = {AddBlogController.class, User.class, Blog.class, BlogService.class, PasswordEncoderConfig.class})
public class AddBlogControllerTest {
    @MockBean
    private BlogService blogservice;
    @Autowired
    private AddBlogController addBlogcontroller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addPageTitleTest() {
        String title = "New Blog - Write a new Blog";
        assertThat(title).isEqualTo(addBlogcontroller.addPageTitle());
    }

    @Test
    public void addUserToModelTest() {
        Object addedUser = new User();
        List<User> lUs = new ArrayList<>();
        lUs.add((User) addedUser);
        assertThat(lUs).isNotEmpty();
        assertThat(addedUser).isEqualTo(addBlogcontroller.addUserToModel(new User()));

    }

    @Test
    public void addBlogToModelTest() {
        Object addBlog = new Blog();
        assertThat(addBlog).isEqualTo(addBlogcontroller.addBlogToModel());
    }

    //needs checking
//	@Test
//	public void openAddBlogPageTest() throws Exception{
//		String AddBlogPage = "AddBlogPage";
//		assertThat(AddBlogPage).isEqualTo(addBlogcontroller.openAddBlogPage());
//
//		this.mockMvc.perform(get("/blog/new"))
//			.andExpect(status().isOk())
//			.andExpect(view().name("AddBlogPage"));
//	}
//	@Test
//	public void saveNewBlogTest() throws Exception{
//		mockMvc.perform(post("/blog/new")
//				.param("title","once up on time in the wonder land")
//				.param("content", "ce up on time in the wonder land")
//				).andExpect(status().isCreated());
//
//	}
}















