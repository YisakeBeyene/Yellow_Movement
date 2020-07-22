package com.yellowmovement.site.controllers;

import com.yellowmovement.site.domains.Post;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/post/new")
public class AddPostController {
    private PostService postService;

    public AddPostController(PostService postService){
        this.postService = postService;
    }

    @ModelAttribute("title")
    public String addPageTitle() {
        return "New Post - Post new News";
    }

    @ModelAttribute("loggedInUser")
    public User addUserToModel(@AuthenticationPrincipal User user) {
        return user;
    }

    @ModelAttribute("newPost")
    public Post addPostToModel() {
        return new Post();
    }


    @GetMapping
    public String openAddPostPage(){

        return "AddPostPage";
    }


    @PostMapping
    public String saveNewPost(@Valid @ModelAttribute("newPost") Post post, Errors errors, @RequestParam("file") MultipartFile[] files){

        if (errors.hasErrors()){
            return "AddPostPage";
        }

        postService.save(post, files[0]);

        return "redirect:/home";
    }



}
