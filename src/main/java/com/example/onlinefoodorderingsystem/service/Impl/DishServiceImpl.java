package com.example.onlinefoodorderingsystem.service.Impl;

import com.example.onlinefoodorderingsystem.entity.Dish;
import com.example.onlinefoodorderingsystem.mapper.DishMapper;
import com.example.onlinefoodorderingsystem.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public Map<Integer,Dish> selectbyid() {

        Map<Integer,Dish> dishMap = new HashMap<>();
        for (int id = 1; id <= 8; id++) {
            Dish dish = dishMapper.selectByid(id);
            if (dish != null) {
                dishMap.put(id, dish);
            }
        }

        return dishMap;
    }

    public void addQuantity(int count){
            dishMapper.addQuantity(count);
    }
    public int subtractQuantity(int count){
        try{

            return dishMapper.subtractQuantity(-count);

        }catch (Exception e) {
            return -1;
        }
    }
//public void addQuantity(int productId, int quantity) {
//    dishMapper.updateQuantity(productId, quantity);
//}
//
//    public int subtractQuantity(int productId, int quantity) {
//        return dishMapper.updateQuantity(productId, -quantity);
//    }
}
