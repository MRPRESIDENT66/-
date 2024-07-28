package com.example.onlinefoodorderingsystem;

import com.example.onlinefoodorderingsystem.controller.OrderingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class DishTest {

    @Autowired
    private OrderingController orderingController;

    @Test
    public void countTest(){
        Map<String,String> map1 = new HashMap<>();
        map1.put("product","5");
        Map<String,String> map2 = new HashMap<>();
        map2.put("product","14");
        orderingController.count(map1);
        orderingController.count(map2);
    }
}
