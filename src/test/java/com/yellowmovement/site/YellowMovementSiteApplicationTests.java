package com.yellowmovement.site;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yellowmovement.site.controllers.HomeController;
import com.yellowmovement.site.controllers.PostContentPageController;
import com.yellowmovement.site.controllers.ProfileController;
import com.yellowmovement.site.controllers.WelcomeController;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.UserService;
import com.yellowmovement.site.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YellowMovementSiteApplicationTests {
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private WelcomeController controller;
	@Autowired
	private ProfileController profileController;
	@Autowired
	private HomeController homeController;
	@Autowired
	private PostContentPageController postController;
	
	@Test
    public void contexLoadsprofile() throws Exception {
        assertThat(profileController).isNotNull();
    }
	
	@Test
    public void contexLoadshome() throws Exception {
        assertThat(homeController).isNotNull();
    }
	
	@Test
    public void contexLoadspost() throws Exception {
        assertThat(postController).isNotNull();
    }
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
	
//	@Before(value = "")
//	 public void initDB() {
//		 {
//			 User newUser = new User( "User 1","example1@example.com",1,"0000","female");
//			 userService.saveUser(newUser);
//		 }
//
//	 }
//	 @Test
//	 public void testUser() {
//		 User user = userService.findUserByEmail("example1@example.com");
//		 assertNotNull(user);
//	 }

}
