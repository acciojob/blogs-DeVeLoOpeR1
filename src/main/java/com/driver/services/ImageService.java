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

        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        String [] screen_Array = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimension = image.getDimensions();
        String [] image_Array = imageDimension.split("X");

        int screen_length = Integer.parseInt(screen_Array[0]);
        int screen_breadth = Integer.parseInt(screen_Array[1]);

        int image_length = Integer.parseInt(image_Array[0]);
        int image_breadth = Integer.parseInt(image_Array[1]);

        return  numberOfImages( screen_length,screen_breadth,image_length, image_breadth);
    }

    public int numberOfImages ( int screen_length,int screen_breadth,int image_length,int image_breadth){
        int len1 = screen_length/image_length;
        int len2 = screen_breadth/image_breadth;

        return len1*len2;
    }


}
