package com.example.onlinefoodorderingsystem.mapper;

import com.example.onlinefoodorderingsystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from users where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into users(username,password,email,created_at,updated_at,phone_number,address)"+"values(#{username},#{password},#{email},now(),now(),#{phone},#{address})")
    void add(String username, String password, String email,String phone,String address);

    @Update("update users set username=#{username},email=#{email},updated_at=#{updatedAt},phone_number=#{phoneNumber},address=#{address} where user_id=#{userId}")
    void update(User user);

    @Update("update users set password=#{password} where user_id=#{userId}")
    void updatePwd(String password,Integer userId);




}
