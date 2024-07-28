package com.example.onlinefoodorderingsystem.service.Impl;

import com.example.onlinefoodorderingsystem.entity.User;
import com.example.onlinefoodorderingsystem.mapper.UserMapper;
import com.example.onlinefoodorderingsystem.service.UserService;
import com.example.onlinefoodorderingsystem.utils.PasswordEncryptor;
import com.example.onlinefoodorderingsystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

import static com.example.onlinefoodorderingsystem.utils.PasswordEncryptor.encryptPassword;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password, String email, String phone ,String address) {
        //加密
        String encryptedPassword = encryptPassword(password);

        userMapper.add(username,encryptedPassword,email,phone,address);
    }

    public void update(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.update(user);

    }

    @Override
    public void updatePwd(String newPassword) {
        Map<String, Object> map = ThreadLocalUtil.getThreadLocal();
        Integer id = (Integer)map.get("UserId");
//        System.out.println(id);
//        System.out.println(newPassword);
        userMapper.updatePwd(encryptPassword(newPassword),id);
    }
}
