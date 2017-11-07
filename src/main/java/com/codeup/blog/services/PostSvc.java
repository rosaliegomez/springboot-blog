package com.codeup.blog.services;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PostSvc")
public class PostSvc {

    private List<Post> posts;

    public PostSvc(){
        posts = new ArrayList<>();
        createDummyPosts();
    }

    private void createDummyPosts(){
        posts.add(new Post("First Title", "This is the description"));
        posts.add(new Post("Title-ish Title", "Lots of text"));
    }

    //Get all the ads
    public List<Post> findAll(){
        return posts;
    }

    public Post findOne(Long id){
        return posts.get((int)(id-1));
    }

    public Post save (Post post){
        post.setId((long) (posts.size()+1));
        posts.add(post);
        return post;
    }
    private void createPosts() {
        this.save(new Post("Example 1", "Long description"));
        this.save(new Post("Example 2", "Another long description"));
    }
}
