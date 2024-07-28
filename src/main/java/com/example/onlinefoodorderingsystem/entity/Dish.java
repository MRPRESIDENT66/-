package com.example.onlinefoodorderingsystem.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Dish {

    private Integer dishId;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
