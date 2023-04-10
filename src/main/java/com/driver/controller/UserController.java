package com.driver.controller;

import com.driver.models.User;
import com.driver.services.GraphHopperMatrixApiExample;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GraphHopperMatrixApiExample graphHopperMatrixApiExample;

    @GetMapping(value = "/getDistanceAndTime")
    public void getDistanceAndTime() throws Exception {
        // 26.922070,75.778885
        // 28.644800,77.216721
        double startLat =26.922070;
        double startLong =75.778885;
        double endLat =28.644800;
        double endLong =77.216721;
        try{
            graphHopperMatrixApiExample.getDistanceAndTime(startLat,startLong,endLat,endLong);
        }
        catch(Exception e){
            System.out.println("Exception Aa Gaye Kuch To Karo");
            System.out.println(e.getMessage());
        }


    }

    @PostMapping("/create") //done
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        userService.createUser(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}") //done
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update") //done
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
       userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/find/{username}")
//    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

}
