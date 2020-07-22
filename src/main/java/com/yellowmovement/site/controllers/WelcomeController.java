package com.yellowmovement.site.controllers;

import javax.validation.Valid;

import com.yellowmovement.site.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.yellowmovement.site.security.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class WelcomeController {

    @Autowired
    private UserService userService;

    @ModelAttribute("title")
    public String addPageTitle() {
        return "Welcome to the Yellow Movement";
    }

    @ModelAttribute(name="account")
    public User user() {
        return new User();
    }

    @ModelAttribute(name="loggedInUser")
    public User loggedInUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/login")
    public String login(){
        return "redirect:/?performing=login";
    }

    @GetMapping
    public String home() {

        System.out.println("\nChecking existence of Admin...\n");
        if (userService.findUserByEmail("admin1@admin.com") == null){
            System.out.println("\nRegistering New Admin...\n");
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin1@admin.com");
            admin.setPassword("0000");
            admin.setSex("female");
            userService.saveUser(admin);

            User adminAgain = userService.findUserByEmail("admin1@admin.com");
            userService.makeAdmin(adminAgain);

        }


        return "index";
    }

    @GetMapping("/createAccount")
    public String openCreateAccountPage(){
        return "redirect:/?performing=createAccount";
    }

    @PostMapping("/createAccount")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.username",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "redirect:/?performing=createAccount&error=true";
        } else {

            userService.saveUser(user);

            model.addAttribute("successMessage", "User has been registered successfully");

            return "redirect:/";
        }
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access_denied";
    }

}
