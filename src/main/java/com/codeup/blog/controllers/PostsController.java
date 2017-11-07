package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {

    private final PostSvc postSvc;

    @Autowired
    public PostsController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }



    @GetMapping("/posts")

    public String showPosts (Model viewModel){
        String output = "";
       List<Post> posts = postSvc.findAll();
        viewModel.addAttribute("posts",posts);
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
    @ResponseBody
    public String showPosts (@PathVariable Long id, Model viewModel){
        Post post = postSvc.findOne(id);
        viewModel.addAttribute("posts", post);

        return "view an individual post " + id;
    }

    @GetMapping("/posts/create")
    public String showCreateForm (Model viewModel){
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPosts (@ModelAttribute Post post){
        postSvc.save(post);
    return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showPostToBeEdited (@PathVariable Long id,  Model viewModel){
        Post post = postSvc.findOne(id);
        viewModel.addAttribute("post", post);
        return "posts/edit";
    }
}
