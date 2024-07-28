package com.example.onlinefoodorderingsystem;

import com.example.onlinefoodorderingsystem.controller.CheckOutController;
import com.example.onlinefoodorderingsystem.entity.OrderItem;
import com.example.onlinefoodorderingsystem.service.CheckOutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CheckoutTest {

    @Autowired
    private CheckOutService checkOutService;

    @Test
    public void ListTest() {
        List<OrderItem> orderItems= checkOutService.getSelectedOrderItems();
        System.out.println(orderItems);
    }

    @Test
    public void totalPriceTest(){
        Integer total=checkOutService.getTotalPrice();
        assertEquals(110, total);
    }
}
