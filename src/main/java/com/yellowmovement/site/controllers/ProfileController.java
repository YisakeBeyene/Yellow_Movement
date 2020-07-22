package com.yellowmovement.site.controllers;

import com.yellowmovement.site.security.User;
import com.yellowmovement.site.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

	UserService userService;

	@Autowired
	public ProfileController(UserService userService){
		this.userService = userService;
	}

	@ModelAttribute("loggedInUser")
	public User addUserToModel(@AuthenticationPrincipal User user, Model model){
		model.addAttribute("title", user.getName());
		return user;
	}

	@GetMapping
	public String showProfile() {
		return "ProfilePage";
	}

	@GetMapping("/uploadImg")
	public String showProfileWithUploadmage() {
		return "redirect:/profile?performing=uploadImg";
	}

	@GetMapping("/edit")
	public String showProfileWithEdit() {
		return "redirect:/profile?performing=edit";
	}

	@PostMapping("/uploadImg")
	public String uploadImg(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile file) {

		log.warn(user.toString());
		if (userService.uploadImage(user, file)){
			return "redirect:/profile?performing=edit";
		}

		return "redirect:/profile?performing=uploadImg&error=upload";
	}

	@PostMapping("/edit")
	public String updateAccount(@Valid @ModelAttribute("loggedInUser") User user, Errors errors){

		if(errors.hasErrors()){
			return "redirect:/profile?performing=edit";
		}

		userService.save(user);
		return "redirect:/profile";
	}

	@GetMapping("/beBlogger")
	public String beBlogger(@Valid @ModelAttribute("loggedInUser") User user, Errors errors){

		if(errors.hasErrors()){
			return "redirect:/profile?performing=edit";
		}
		userService.makeBlogger(user);
		return "redirect:/profile";
	}

	@GetMapping("/unbeBlogger")
	public String unbeBlogger(@Valid @ModelAttribute("loggedInUser") User user, Errors errors){

		if(errors.hasErrors()){
			return "redirect:/profile?performing=edit";
		}
		userService.unmakeBlogger(user);
		return "redirect:/profile";
	}
}
