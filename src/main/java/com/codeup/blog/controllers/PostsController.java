package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UsersRepository;
import com.codeup.blog.repositories.PostsRepository;
import com.codeup.blog.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostsController {

    private final PostSvc service;
    private final UsersRepository userDao;
    private final PostsRepository postDao;

    @Autowired
    public PostsController(PostSvc service,  UsersRepository userDao, PostsRepository postDao) {
        this.service = service;
        this.userDao = userDao;
        this.postDao =postDao;
    }

    @Autowired
    PostsRepository postsRepository;



    @GetMapping("/posts")

    public String showPosts (Model viewModel, @ModelAttribute Post post){
        String output = "";
//
        List<Post> posts = (List<Post>) service.findAll();
        viewModel.addAttribute("posts",service.findAll());

//        viewModel.addAttribute("posts",postsRepository);



//        Post postId = postDao.findById(post.getId());


//        List<Post>postsIds = postsDao.findById(id);
//        List <Long> postIds = (List<Long>) service.findById(post.getId());
//        viewModel.addAttribute("postid", service.findById(post.getId()));

//
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
    public String createPosts (@Valid Post post, Errors validation, Model viewModel){

        if(post.getTitle().endsWith("?")){
            validation.rejectValue(
                    "title",
                    "post.title",
                    "Invalid character!"
            );
        }

        if(validation.hasErrors()){
            viewModel.addAttribute("errors",validation);
            viewModel.addAttribute("posts",post);
            return "posts/create";
        }


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

        return "redirect:/posts";
//        return "redirect:/posts/" + post.getId();
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