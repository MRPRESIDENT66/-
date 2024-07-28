package com.example.onlinefoodorderingsystem.service;

import com.example.onlinefoodorderingsystem.entity.Order;
import com.example.onlinefoodorderingsystem.entity.OrderItem;

import java.util.List;

public interface CheckOutService {
    List<OrderItem> getSelectedOrderItems();
    Integer getTotalPrice();
    void deleteOrderItems();
    void createOrderItem(Integer userId,Integer totalAmount);
    List<Order> getOrder(Integer userId);
}
