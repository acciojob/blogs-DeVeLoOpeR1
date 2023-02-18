package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog;
        try{
            blog = blogRepository2.findById(blogId).get();
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);


        return image;

    }

    public void deleteImage(Integer id){

        try{
            imageRepository2.deleteById(id);
        }
        catch(Exception e)
        {
            e.getMessage();
        }

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        Image image;
        try {
            image = imageRepository2.findById(id).get();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
        if (image.equals(null) && screenDimensions.equals(null))
            return 0;

        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count;
        int screenX = Integer.parseInt(String.valueOf(screenDimensions.charAt(0)));
        int screenY = Integer.parseInt(String.valueOf(screenDimensions.charAt(2)));
        int screenXY = screenX * screenY; //4*4 = 16

        int imageX = Integer.parseInt(String.valueOf(image.getDimensions().charAt(0)));
        int imageY = Integer.parseInt(String.valueOf(image.getDimensions().charAt(2)));
        int imageXY = imageX * imageY; // 2*2 = 4

        try {
            count = screenXY / imageXY;
        } catch (Exception e){
            e.getMessage();
            return 0;
        }
        return count;
    }
}
