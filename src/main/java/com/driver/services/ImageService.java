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

        Image images;
        try {
            images = imageRepository2.findById(id).get();
        } catch (Exception e) {
            e.getMessage();
            return 0;
        }
        String image = images.getDimensions();


        if(image.length()==0||screenDimensions.length()==0)
            return 0;


        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count;
        int screenX;
        int screenY;
        int screenXY=0;
        int imageX;
        int imageY;
        int imageXY=0;

        if(screenDimensions.length()<=3){


            screenX = Integer.parseInt(String.valueOf(screenDimensions.charAt(0)));
            screenY = Integer.parseInt(String.valueOf(screenDimensions.charAt(2)));
            screenXY = screenX * screenY; //4*4 = 16

        }
        else{ //two digit data
            screenX = Integer.parseInt(screenDimensions.substring(0,3));
            screenY = Integer.parseInt(screenDimensions.substring(4));
            screenXY = screenX * screenY;

        }


        if(image.length()<=3){
            imageX = Integer.parseInt(String.valueOf(image.charAt(0)));
            imageY = Integer.parseInt(String.valueOf(image.charAt(2)));
            imageXY = imageX * imageY; // 2*2 = 4
        }
        else{

            screenX = Integer.parseInt(image.substring(0,3));
            screenY = Integer.parseInt(image.substring(4));
            screenXY = screenX * screenY;
        }

        if(screenXY == 0 || imageXY ==0)
        {
            return 0;
        }

        count = screenXY / imageXY;
        return count;


    }
}
