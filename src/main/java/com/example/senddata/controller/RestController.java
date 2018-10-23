package com.example.senddata.controller;


import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

import com.example.senddata.model.User;
import com.example.senddata.service.UserService;
import com.example.senddata.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    UserService userService;

    @RequestMapping("/user/")
    public ResponseEntity getUser(@RequestParam(value="id") int id) {
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            System.out.println("Unable to update. User with id {} not found."+ id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateData(@PathVariable int id, @RequestBody User user){
        if(user.getName() == null || user.getName().isEmpty()){
            return new ResponseEntity(new CustomErrorType("The form was not submitted due to the error(s).Field name can't be empty"),
                    HttpStatus.BAD_REQUEST);
        }
        if(user.getEmail() == null){
            return new ResponseEntity(new CustomErrorType("The form was not submitted due to the following error(s).Field email can't be empty"),
                    HttpStatus.BAD_REQUEST);
        }
        if(!isValid(user.getEmail())){
            return new ResponseEntity(new CustomErrorType("The form was not submitted due to the following error(s).The Email Address is in an invalid format, expected format: example@email.com"),
                    HttpStatus.BAD_REQUEST);
        }

        System.out.println("Updating User with id " + id);
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            System.out.println("Unable to update. User with id not found."+ id);
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());


        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);

    }
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}