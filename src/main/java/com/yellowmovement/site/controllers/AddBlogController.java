package com.yellowmovement.site.controllers;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/blog/new")
public class AddBlogController {
    private BlogService blogService;

    public AddBlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @ModelAttribute("title")
    public String addPageTitle() {
        return "New Blog - Write a new Blog";
    }

    @ModelAttribute("loggedInUser")
    public User addUserToModel(@AuthenticationPrincipal User user) {
        return user;
    }

    @ModelAttribute("newBlog")
    public Blog addBlogToModel() {
        return new Blog();
    }


    @GetMapping
    public String openAddBlogPage(){

        return "AddBlogPage";
    }


    @PostMapping
    public String saveNewBlog(@Valid @ModelAttribute("newBlog") Blog blog, Errors errors, @RequestParam("file") MultipartFile[] files, @AuthenticationPrincipal User user){

        if (errors.hasErrors()){
            return "AddBLogPage";
        }

        blogService.save(blog, files[0], user);

        return "redirect:/blog";
    }



}
