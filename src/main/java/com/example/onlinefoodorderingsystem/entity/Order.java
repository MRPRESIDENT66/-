package com.example.onlinefoodorderingsystem.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class Order {

    private Integer orderId;
    private Integer userId;
    private Double totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
