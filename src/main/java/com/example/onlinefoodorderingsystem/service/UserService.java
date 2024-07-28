package com.example.onlinefoodorderingsystem.service;

import com.example.onlinefoodorderingsystem.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findByUserName(String username);
    void register(String username, String password, String email,String address, String phone );
    void update(User user);
    void updatePwd(String newPassword);
}
