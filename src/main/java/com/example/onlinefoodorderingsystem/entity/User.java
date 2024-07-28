package com.example.onlinefoodorderingsystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {

    private Integer userId;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
