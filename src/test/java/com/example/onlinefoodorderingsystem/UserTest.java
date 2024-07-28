package com.example.onlinefoodorderingsystem;

import com.example.onlinefoodorderingsystem.controller.UserController;
import com.example.onlinefoodorderingsystem.entity.Result;
import com.example.onlinefoodorderingsystem.entity.User;
import com.example.onlinefoodorderingsystem.service.Impl.UserServiceImpl;
import com.example.onlinefoodorderingsystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
public class UserTest {


    @Autowired
    private UserController userController;


    @Test
    public void testRegister() {
        userController.register("newUser", "password123",
                "email@example.com", "1234567890", "123 Street");

    }

    @Test
    public void testLogin() {
        userController.login("newUser", "password123");
    }








}
