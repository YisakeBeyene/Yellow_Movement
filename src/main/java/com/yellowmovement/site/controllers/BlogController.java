package com.yellowmovement.site.controllers;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @ModelAttribute("title")
    public String addPageTitle() {
        return "Blogs - Blogger Posts";
    }

//    @ModelAttribute("categoriesList")
//    public List<String> addCategoriesToModel() {
//        List<String> categories = new ArrayList<>();
//
//        blogService.findCategoriesList().forEach(i -> categories.add(i));
//
//        return categories;
//    }

    @GetMapping
    public String addBlogsToModel(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "4") int size, Model model){
        PageRequest pageRequest = PageRequest.of(
                page,size, Sort.by("bloggedDate").descending());

        model.addAttribute("dataList", blogService.findAll(pageRequest));
        if (blogService.findAll(pageRequest).isEmpty()){
            model.addAttribute("paginate", "no");
        }
        model.addAttribute("currentPage", page);

        return "BlogsPage";
    }

    @ModelAttribute("loggedInUser")
    public User addUserToModel(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/delete/{blogId}")
    public String deleteBlog(@PathVariable("blogId") Long blogId) {

        blogService.deleteById(blogId);

        return "redirect:/blog";
    }

    @PostMapping
    public String blogsProcessor() {
        return "BlogsPage";
    }


    @GetMapping("/category")
    public String searchBlogs(@RequestParam("keyword") String keyword, Model model){
        List<Blog> blogsByQuery = blogService.searchPosts(keyword);

        model.addAttribute("dataList", blogsByQuery);
        model.addAttribute("paginate", "no");

        return "BlogsPage";
    }

}
