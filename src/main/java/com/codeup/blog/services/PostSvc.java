package com.codeup.blog.services;

import com.codeup.blog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void delete(long id){
        postDao.delete(id);
    }
}
