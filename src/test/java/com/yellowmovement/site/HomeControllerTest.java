package com.yellowmovement.site;

import com.yellowmovement.site.domains.Comment;
import com.yellowmovement.site.domains.Post;
import com.yellowmovement.site.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@MockBean
	private PostRepository postRepository;
	@Test
	public void addCategoriesToModelTest() {
		List<String> categories = new ArrayList<>();
		ArrayList<String> cat = new ArrayList<>();
		cat.add("Science and Technology");
		cat.add("Feminist Clubs");
		cat.add("General");
		cat.add("Entertainment");
		Mockito.when(postRepository.findCategoriesList()).thenReturn(cat);
		postRepository.findCategoriesList().forEach(i -> categories.add(i));
		assertThat(categories).isNotEmpty();
		assertThat(categories).contains("Science and Technology", "General", "Entertainment");
	}

	@Test
	public void addPostsToModel() {
		List<Post> posts = new ArrayList<>();
		ArrayList<Post> pos = new ArrayList<>();
		List<Comment> comment = new ArrayList<>();
		Post post1 = new Post((long) 1, "Post1", "Here post one", "Entertainment", "Image/a.jpg", new Date(), comment);
		posts.add(post1);

		assertThat(posts).isNotEmpty();
		assertThat(posts).contains(post1);
	}

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void addUserToModel() throws Exception {
		this.mockMvc.perform(get("/")).
				andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void openPostPageTest() {
		ArrayList<Post> posts = new ArrayList<>();
		List<Comment> comment = new ArrayList<>();
		Post post1 = new Post((long) 1, "Post1", "Here post one", "Entertainment", "Image/a.jpg", new Date(), comment);
		posts.add(post1);
		Mockito.when(postRepository.searchPosts("Post1")).thenReturn(posts);
		assertThat(posts).isNotEmpty();
	}
}
