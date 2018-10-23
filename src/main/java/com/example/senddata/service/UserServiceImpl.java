package com.example.senddata.service;

import com.example.senddata.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
@Service("userService")
public class UserServiceImpl implements UserService {
    private static final AtomicInteger counter = new AtomicInteger();

    private static List<User> users;

    static{
        users= populateSomeUsers();
    }

    public  List<User> listUsers(){
        return users;
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        users.set(index, user);
    }

    public User findById(int id) {
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    private static List<User> populateSomeUsers(){
        List<User> users = new ArrayList<User>();
        users.add(new User(1,"Alexa","alexa@amazon.com"));
        users.add(new User(2,"Siri","siri@apple.com"));
        users.add(new User(3,"John", "john@john.com"));
        return users;
    }

}
