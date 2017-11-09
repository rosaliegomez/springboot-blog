package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long>{
//    User findByTitle(String title);
//
//    @Query("from User u where u.id=?2")
//    public User findById(long id);


}