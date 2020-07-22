package com.yellowmovement.site.controllers;

import java.util.ArrayList;
import java.util.List;

import com.yellowmovement.site.repositories.PostRepository;
import com.yellowmovement.site.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yellowmovement.site.domains.Post;
import com.yellowmovement.site.security.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {

    private PostService postService;

    public HomeController(PostService postService){
        this.postService = postService;
    }

    @ModelAttribute("title")
    public String addPageTitle() {
        return "Home - News and Announcements";
    }

    @ModelAttribute("categoriesList")
    public List<String> addCategoriesToModel() {
        List<String> categories = new ArrayList<>();

        postService.findCategoriesList().forEach(i -> categories.add(i));

        return categories;
    }

    @GetMapping
    public String addPostsToModel(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "4") int size, Model model){
        PageRequest pageRequest = PageRequest.of(
                page,size, Sort.by("postedDate").descending());

        model.addAttribute("dataList", postService.findAll(pageRequest));
        if (postService.findAll(pageRequest).isEmpty()){
            model.addAttribute("paginate", "no");
        }
        model.addAttribute("currentPage", page);

        return "HomePage";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId){

        postService.deleteById(postId);

        return "redirect:/home";
    }

    @ModelAttribute("loggedInUser")
    public User addUserToModel(@AuthenticationPrincipal User user) {
        return user;
    }


    @PostMapping
    public String homeProcessor() {
        return "HomePage";
    }


    @GetMapping("/category")
    public String openPostPage(@RequestParam("keyword") String keyword, Model model){
        List<Post> postsByQuery = postService.searchPosts(keyword);

        model.addAttribute("dataList", postsByQuery);
        model.addAttribute("paginate", "no");

        return "HomePage";
    }

}
