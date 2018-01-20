package com.codeup.blog.controllers;


import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UsersController {
    private PasswordEncoder encoder;
    private UsersRepository repository;

    public UsersController(PasswordEncoder encoder, UsersRepository repository){
        this.encoder = encoder;
        this.repository = repository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "users/registration";
    }

//**********  Use as example for how to validate username and email***********
//    @PostMapping("/posts/create")
//    public String createPosts (@Valid Post post, Errors validation, Model viewModel) {
//
//        if (post.getTitle().endsWith("?")) {
//            validation.rejectValue(
//                    "title",
//                    "post.title",
//                    "Invalid character!"
//            );
//        }
//
//        if (validation.hasErrors()) {
//            viewModel.addAttribute("errors", validation);
//            viewModel.addAttribute("posts", post);
//            return "posts/create";
//        }
//    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        repository.save(user);

        return "redirect:/login";
    }

}
