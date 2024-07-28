package com.example.onlinefoodorderingsystem.service;

import com.example.onlinefoodorderingsystem.entity.Dish;

import java.util.Map;

public interface DishService {
    Map<Integer, Dish> selectbyid();
    void addQuantity(int count);
    int subtractQuantity(int count);
//    void addQuantity(int productId, int quantity);
//    int subtractQuantity(int productId, int quantity);

}
