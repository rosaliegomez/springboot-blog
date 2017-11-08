package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post, Long>{
    Post findByTitle(String title);

    @Query("from Post p where p.id=?1")
    public Post findById(long id);


}
