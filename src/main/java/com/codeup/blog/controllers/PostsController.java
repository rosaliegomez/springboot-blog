package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostsController {

    private final PostRepository postDao;

    @Autowired
    public PostsController(PostRepository postDao) {
        this.postDao = postDao;
    }



    @GetMapping("/posts")

    public String showPosts (Model viewModel){
        String output = "";
       List<Post> posts = (List<Post>) postDao.findAll();
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
    public String showPosts (@PathVariable Long id, Model viewModel){
        Post post = postDao.findOne(id);
        viewModel.addAttribute("post", post);

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm (Model viewModel){
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPosts (@ModelAttribute Post post){
        postDao.save(post);
    return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showPostToBeEdited (@PathVariable Long id,  Model viewModel){
        Post post = postDao.findOne(id);
        viewModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post, @PathVariable Long id){
        postDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("posts/{id}/delete")
    public String showPostToBeDeleted (@PathVariable Long id, Model viewModel){
        Post post = postDao.findById(id);
        viewModel.addAttribute("post", post);

        return "posts/delete";
    }
    @PostMapping("/posts/{id}/delete")
    public String deletePosts(@ModelAttribute Post post, @PathVariable Long id){
        postDao.delete(id);
        return "redirect:/posts";
    }
}