package com.yellowmovement.site;

import com.yellowmovement.site.controllers.PostContentPageController;
import com.yellowmovement.site.domains.Comment;
import com.yellowmovement.site.domains.Post;
import com.yellowmovement.site.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(PostContentPageController.class)
@ContextConfiguration(classes = {PostRepository.class})
public class PostContentPageControllerTest {

    @MockBean
    private PostContentPageController sampleService;

    @MockBean
    private PostRepository postRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void openPostPageTest() throws Exception {
        Long x = (long) 3;
        Optional<Post> postJustForTest = null;
        List<Comment> comment = new ArrayList<>();
        Post post1 = new Post((long) 1, "Post1", "Here post one", "Entertainment", "Image/a.jpg", new Date(), comment);
        when(postRepo.findById(x)).thenReturn(postJustForTest);

        mockMvc.perform(get("/post/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("PostContentPage"));
    }

}
