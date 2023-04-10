package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;


    @Autowired
    UserRepository userRepository1;

//    public List<Blog> showBlogs(){
//        //find all blogs
//
//    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time
        Blog blog = new Blog();

        //updating the blog details

        blog.setTitle(title);
        blog.setContent(content);
        User user;
        try{
            user = userRepository1.findById(userId).get();
        }
        catch(Exception e){
            e.getMessage();
            return;
        }
        blog.setUser(user);
        blog.setUser(user);

        //Updating the userInformation and changing its blogs
         user.getBlog().add(blog);

         userRepository1.save(user);

    }
/*
    public Blog findBlogById(int blogId){
        //find a blog
    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
    } */

    public void deleteBlog(int blogId){
        try{
            blogRepository1.deleteById(blogId);
        }
        catch(Exception e){
            e.getMessage();
        }
    }
}
