package com.example.onlinefoodorderingsystem.controller;

import com.example.onlinefoodorderingsystem.entity.Order;
import com.example.onlinefoodorderingsystem.entity.OrderItem;
import com.example.onlinefoodorderingsystem.entity.Result;
import com.example.onlinefoodorderingsystem.service.CheckOutService;
import com.example.onlinefoodorderingsystem.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/check")
@Component
public class CheckOutController {

    @Autowired
    private CheckOutService checkOutService;


    @GetMapping("/info")
    public Result info() {
        List<OrderItem> orderItem=checkOutService.getSelectedOrderItems();
        if (orderItem != null) {
            return Result.success(orderItem);
        } else {
            return Result.error("No order items found");
        }
    }

    @GetMapping("/total")
    public Result getTotalPrice() {
        return Result.success(checkOutService.getTotalPrice());
    }
    //删除订单物品中的数量信息，全部归零
    @PostMapping("/delete")
    public Result delete() {
        checkOutService.deleteOrderItems();
        return Result.success("购买成功");
    }


    //创建相对应的订单表
    @PostMapping("/create")
    public Result create() {
        Map<String,Object> map= ThreadLocalUtil.getThreadLocal();
        Integer userId = (Integer) map.get("UserId");
        int TotalPrice=checkOutService.getTotalPrice();
        checkOutService.createOrderItem(userId,TotalPrice);
        return Result.success("订单创建成功");
    }


    //获取订单表中的信息
    @GetMapping("/checkoutinfo")
    public Result checkoutInfo() {
        Map<String,Object> map= ThreadLocalUtil.getThreadLocal();
        Integer userId = (Integer) map.get("UserId");
        List<Order> order =checkOutService.getOrder(userId);
        return Result.success(order);
    }


}
