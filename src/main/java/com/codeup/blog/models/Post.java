package com.codeup.blog.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Posts must have a title")
    @Size(min=3, message = "A post title must have at least 3 characters.")
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Posts must have a description.")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post(){

    }

    public Post(Long id, String title, String description){
        this.id=id;
        this.title = title;
        this.description = description;
    }

    public Post(String title, String description){
        this.title = title;
        this.description = description;
    }
//    public Post(Long id, String title, String description){
//        this.id = id;
//        this.title = title;
//    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }


    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }
}
