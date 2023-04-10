package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public void getDistanceAndTime(double startLat, double startLong, double endLat, double endLong) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graphhopper.com/api/1/matrix?from_point=26.922070,75.778885&to_point=28.644800,77.216721&type=json&profile=car&out_array=weights&out_array=times&out_array=distances&key=de06056d-de02-400d-944d-a6859fac4325")
                .get()
                .build();

        Response response = client.newCall(request).execute();


//        Long distance = 0L;
//        Long time = 0L;
        System.out.println(response.body());



    }

    public void createUser(User user){

        userRepository3.save(user);

    }

    public void deleteUser(int userId){
        try{
            userRepository3.deleteById(userId);
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    public void updateUser(User user){

        userRepository3.save(user);

    }



//    public User findUserByUsername(String username){
//        return userRepository3.findByUsername(username);
//    }

}
