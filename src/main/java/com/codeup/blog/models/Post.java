package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

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
}
