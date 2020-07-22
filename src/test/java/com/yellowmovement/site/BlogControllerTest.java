package com.yellowmovement.site;

import com.yellowmovement.site.configurations.PasswordEncoderConfig;
import com.yellowmovement.site.controllers.BlogController;
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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(BlogController.class)
@ContextConfiguration(classes = {Blog.class, BlogController.class, PasswordEncoderConfig.class, BlogService.class, User.class})
public class BlogControllerTest {
    @MockBean
    private BlogService blogservice;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BlogController blog;

    @Test
    public void addPageTitleTest() {
        String title = "Blogs - Blogger posts";
        assertThat(title).isEqualTo(blog.addPageTitle());
    }
}
