package com.example.onlinefoodorderingsystem.controller;

import com.example.onlinefoodorderingsystem.entity.Result;
import com.example.onlinefoodorderingsystem.entity.User;
import com.example.onlinefoodorderingsystem.service.UserService;
import com.example.onlinefoodorderingsystem.utils.JwtUtil;
import com.example.onlinefoodorderingsystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.onlinefoodorderingsystem.utils.PasswordEncryptor.checkPassword;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    //public Result register(@Pattern(regexp = "^\\s{5,16}$") String username, @Pattern(regexp = "^\\s{5,16}$")String password, String email){
    public Result register(String username, String password, String email,String phone,String address){

        User u=userService.findByUserName(username);
        if(u==null){

            userService.register(username,password,email,phone,address);
            return Result.success();
        }
        else {

            return Result.error("用户名已被占用");

        }
    }
    @PostMapping("/login")
    public Result<String> login(String username, String password){
        User loginUser=userService.findByUserName(username);

        if(loginUser==null){
            return Result.error("未找到该用户");
        }
        else {
            boolean isPasswordMatch=checkPassword(password, loginUser.getPassword());
            if (isPasswordMatch) {
                Map<String,Object> claims =new HashMap<>();
//                int userIdAsInt = loginUser.getUserId();
//                String userIdStr = String.valueOf(userIdAsInt);

//                Integer userid=loginUser.getUserId();
//                String userIdStr = String.valueOf(userid);
//                claims.put("UserId",userIdStr);
                claims.put("UserId",loginUser.getUserId());
                claims.put("username",loginUser.getUsername());
                String token=JwtUtil.genToken(claims);


//                Map<String, Object> claimss= JwtUtil.parseToken(token);
//                System.out.println(claimss.get("UserId"));

                return Result.success(token);
            }
            return Result.error("密码错误");
        }

    }


    //登录之后的操作
    @GetMapping("/userinfo")
    public Result<User> getCurrentUser(){
        //public Result<User> getCurrentUser(@RequestHeader(name="Authorization")String token){
        Map<String,Object> map= ThreadLocalUtil.getThreadLocal();
        String username=(String)map.get("username");
        User CurrentUser= userService.findByUserName(username);
        return Result.success(CurrentUser);
    }

    @PutMapping("/update")
    public Result updateUser(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){

        if (params == null) {
            return Result.error("接收到的参数为空");
        }
        String oldPassword=params.get("old_pwd");
        String newPassword=params.get("new_pwd");
        String Repassword=params.get("re_pwd");

        if(!StringUtils.hasLength(oldPassword)||!StringUtils.hasLength(newPassword)||!StringUtils.hasLength(Repassword)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map= ThreadLocalUtil.getThreadLocal();
        String username=(String)map.get("username");
//        System.out.println(username);
        User loginuser=userService.findByUserName(username);
        boolean isPasswordMatch=checkPassword(oldPassword, loginuser.getPassword());
        if(!isPasswordMatch){
            return Result.error("原密码填写不正确");
        }
        if (!Repassword.equals(newPassword)) {
            return Result.error("两次密码输入不一致");
        }
        userService.updatePwd(newPassword);
        return Result.success("修改成功！");

    }






}
