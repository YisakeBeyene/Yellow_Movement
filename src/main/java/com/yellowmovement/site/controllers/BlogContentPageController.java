package com.yellowmovement.site.controllers;

import com.yellowmovement.site.domains.Blog;
import com.yellowmovement.site.domains.Comment;
import com.yellowmovement.site.domains.Post;
import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.BlogService;
import com.yellowmovement.site.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogContentPageController {

    private BlogService blogService;
    private CommentService commentService;

    public BlogContentPageController(BlogService blogService, CommentService commentService){
        this.blogService = blogService;
        this.commentService = commentService;
    }


    @ModelAttribute("loggedInUser")
    public User addUserToModel(@AuthenticationPrincipal User user) {
        return user;
    }

    @ModelAttribute("comment")
    public Comment addCommentToModel() {
        return new Comment();
    }


    @GetMapping("/{blogId}")
    public String openPostPage(@PathVariable("blogId") Long blogId, Model model){
        Optional<Blog> currentBlog = blogService.findById(blogId);

        if (currentBlog.isPresent()){
            model.addAttribute("currentBlog", currentBlog.get());

            model.addAttribute("title", currentBlog.get().getTitle());
        }

        return "BlogContentPage";
    }


    @PostMapping("/comment")
    public String openBlogPage(@Valid @ModelAttribute("comment") Comment comment, Errors errors, @RequestParam("blogId") Long blogId, @AuthenticationPrincipal User user, Model model){
        Optional<Blog> currentBlog = blogService.findById(blogId);
        Blog blog = currentBlog.get();;


        model.addAttribute("currentBlog", blog);

        if (errors.hasErrors()){
            log.info(comment.toString());
            log.info(blog.toString());
            return "BlogContentPage";
        }


        comment.setCommenter(user);

        commentService.save(comment);
        blog.getComments().add(comment);

        blogService.save(blog);

        model.addAttribute("comment", new Comment());

        return "redirect:/blog/"+blogId;
    }


}
