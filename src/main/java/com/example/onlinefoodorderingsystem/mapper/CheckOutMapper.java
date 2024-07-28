package com.example.onlinefoodorderingsystem.mapper;


import com.example.onlinefoodorderingsystem.entity.Order;
import com.example.onlinefoodorderingsystem.entity.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckOutMapper {

    @Select("SELECT oi.dish_id, oi.quantity " +
            "FROM order_items oi " +
            "WHERE oi.order_id IS NULL AND oi.quantity > 0")
    List<OrderItem> getSelectedOrderItems();


    @Insert("INSERT INTO orders (user_id, total_amount, status, created_at, updated_at) " +
            "VALUES (#{userId}, #{totalAmount}, '已支付', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)")
//    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    void createOrder(Integer userId,Integer totalAmount);

    @Update("UPDATE order_items SET order_id = #{orderId} " +
            "WHERE order_id IS NULL AND quantity > 0")
    void updateOrderItems(@Param("orderId") Integer orderId);


    @Update("update order_items set quantity = 0")
    void deleteOrderItems();


    @Select("SELECT COALESCE(SUM(price * quantity),0) FROM order_items WHERE quantity > 0")
    Integer getTotalPrice();

    @Select("select * from orders where user_id=#{userId}")
    List<Order> getOrder(Integer userId);
}
