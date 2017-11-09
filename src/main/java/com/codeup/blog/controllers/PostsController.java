package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {

    private final PostSvc service;
    private final UserRepository userDao;

    @Autowired
    public PostsController(PostSvc service, UserRepository userDao) {
        this.service = service;
        this.userDao = userDao;
    }



    @GetMapping("/posts")

    public String showPosts (Model viewModel){
        String output = "";
       List<Post> posts = (List<Post>) service.findAll();
        viewModel.addAttribute("posts",service.findAll());
//        postSvc.save(new Post("New title", "Newish description"));
//
//        for (Post post: posts){
//            output += post.getTitle() + "<br/>" + post.getDescription()+"<br/>";
//        }
        return "posts/index";

    }

//
//    Post("New title","new description"PostSvc postSvc) {
//        this.postSvc = null;
//    });
//
    @GetMapping("/posts/{id}")
    public String showPosts (@PathVariable long id, Model viewModel){
        Post post = service.findById(id);
        viewModel.addAttribute("post", service.findById(id));

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm (Model viewModel){
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPosts (@ModelAttribute Post post){
        User user = userDao.findOne(2L);
        post.setUser(user);
        service.save(post);
    return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showPostToBeEdited (@PathVariable Long id,  Model viewModel){
        Post existingPost = service.findById(id);
        viewModel.addAttribute("post", existingPost);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post, @PathVariable Long id){
        post.setId(id);
        service.save(post);
        return "redirect:/posts" + post.getId();
    }

    @GetMapping("posts/{id}/delete")
    public String showPostToBeDeleted (@PathVariable Long id, Model viewModel){
        Post post = service.findById(id);
        viewModel.addAttribute("post", post);

        return "posts/delete";
    }
    @PostMapping("/posts/{id}/delete")
    public String deletePosts(@ModelAttribute Post post, @PathVariable Long id){
        service.delete(id);
        return "redirect:/posts";
    }
}