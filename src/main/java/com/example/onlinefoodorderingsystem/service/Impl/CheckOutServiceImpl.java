package com.example.onlinefoodorderingsystem.service.Impl;

import com.example.onlinefoodorderingsystem.entity.Order;
import com.example.onlinefoodorderingsystem.entity.OrderItem;
import com.example.onlinefoodorderingsystem.mapper.CheckOutMapper;
import com.example.onlinefoodorderingsystem.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    @Autowired
    private CheckOutMapper checkOutMapper;


    @Override
    public List<OrderItem> getSelectedOrderItems() {
        List<OrderItem> orderItem=checkOutMapper.getSelectedOrderItems();
        if(orderItem==null){
            System.out.println("没找到！！！！！");
        }
        //System.out.println(orderItem);
        return checkOutMapper.getSelectedOrderItems();
    }

    public Integer getTotalPrice(){
        return checkOutMapper.getTotalPrice();
    }

    @Override
    public void deleteOrderItems() {
        checkOutMapper.deleteOrderItems();
    }

    public void createOrderItem(Integer userId,Integer totalAmount){
        checkOutMapper.createOrder(userId,totalAmount);
    }

    public List<Order> getOrder(Integer userId){
        return checkOutMapper.getOrder(userId);
    }
}
