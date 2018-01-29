package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostsRepository extends CrudRepository <Post, Long>{
    Post findByTitle(String title);

    @Query("from Post p where p.id=?1")
    public Post findById(long id);

    @Query("select p from Post p order by p.id desc")
    List<Post> postsInReverse();




}
