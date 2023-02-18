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

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
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
            return null;
        }
        blog.setUser(user);

        //Updating the userInformation and changing its blogs
        user.getBlog().add(blog);

        userRepository1.save(user);

        return blog;

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        try{
            blogRepository1.deleteById(blogId);
        }
        catch(Exception e){
            e.getMessage();
        }

    }
}
