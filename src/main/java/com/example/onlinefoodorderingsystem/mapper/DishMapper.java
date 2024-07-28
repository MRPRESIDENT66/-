package com.example.onlinefoodorderingsystem.mapper;

import com.example.onlinefoodorderingsystem.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DishMapper {
    @Select("select * from dishes where dish_id=#{dish_id}")
    Dish selectByid(int dish_id);

    @Update("update order_items set quantity=quantity+1 where dish_id=#{dish_id}")
    void addQuantity(int dish_id);


    @Update("update order_items set quantity=quantity-1 where dish_id=#{dish_id} and quantity>0")
    int subtractQuantity(int dish_id);

    @Update("UPDATE order_items SET quantity = quantity + #{quantity} WHERE product_id = #{productId} AND quantity + #{quantity} >= 0")
    int updateQuantity(int productId, int quantity);


}
