package com.codeup.blog.services;

import com.codeup.blog.SecurityConfiguration;
import com.codeup.blog.models.Post;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.codeup.blog.repositories.PostsRepository;
import java.util.ArrayList;
import java.util.List;

@Service("PostSvc")
public class PostSvc {

    private final PostsRepository postDao;

//    public PostSvc(){
//        posts = new ArrayList<>();
//        createDummyPosts();
//    }

    @Autowired
    public PostSvc(PostsRepository postDao){
        this.postDao = postDao;
    }


    //Get all the ads
    public Iterable<Post> findAll(){
        return postDao.findAll();
    }

    public Post findById(Long id)
    {
        return postDao.findOne(id);
    }

    public Post save (Post post){
        return postDao.save(post);
    }

//    public void save(Post post){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
    public void delete(long id){
        postDao.delete(id);
    }

    public boolean userMatch (User user){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) return false;
        User loggedIn = (User) principal;
        if (user.getUsername().equals(loggedIn.getUsername())){
            return true;
        } else{
            return false;
        }
    }
}
