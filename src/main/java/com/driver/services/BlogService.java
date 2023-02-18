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

        User user = userRepository1.findById(userId).get();
        Blog blog = new Blog(user,title,content);

        // here we are saving the userRepository and due to cascading effect the child (blog) will be automatically saved
        userRepository1.save(user);



//        //setting the blog attributes
//        blog.setUser(user);
//        blog.setTitle(title);
//        blog.setContent(content);
//        blog.setPubDate(new Date());

        //updating the blog list info of user also
//        List<Blog> currentBLogs = user.getBlogList();
//        currentBLogs.add(blog);
//        user.setBlogList(currentBLogs);

        user.getBlogList().add(blog);
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
