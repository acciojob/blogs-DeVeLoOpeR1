package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository2;
    @Autowired
    BlogRepository blogRepository;

    public Image createAndReturn(Blog blog, String description, String dimensions){
        //create an image based on given parameters and add it to the imageList of given blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimension(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository.save(blog);


        return image;
    }

    public void deleteImage(int id){

        try{
            imageRepository2.deleteById(id);
        }
        catch(Exception e)
        {
            e.getMessage();
            return;
        }

    }

//    public Image findById(int id) {
//
//    }

    public int countImagesInScreen(Image image, String screenDimensions) {
        if(image.equals(null))
            return 0;

        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count = 0 ;
        int screenX = Integer.parseInt(String.valueOf(screenDimensions.charAt(0)));
        int screenY = Integer.parseInt(String.valueOf(screenDimensions.charAt(2)));
        int screenXY = screenX * screenY; //4*4 = 16

        int imageX = Integer.parseInt(String.valueOf(image.getDimension().charAt(0)));
        int imageY = Integer.parseInt(String.valueOf(image.getDimension().charAt(2)));
        int imageXY = imageX * imageY; // 2*2 = 4

        count  = screenXY / imageXY;
        return count;




        //In case the image is null, return 0

    }
}
