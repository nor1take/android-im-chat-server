package com.android.service;

import com.android.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User selectByName(String username);

    User login(String username, String password);

    boolean add(User user);
}


