package com.example.senddata.service;

import com.example.senddata.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    void updateUser(User user);
    User findById(int id);

}
