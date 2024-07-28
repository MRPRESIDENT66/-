package com.example.onlinefoodorderingsystem.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderItem {

    private Long orderItemId;
    private Long orderId;
    private Integer dishId;
    private Integer quantity;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
