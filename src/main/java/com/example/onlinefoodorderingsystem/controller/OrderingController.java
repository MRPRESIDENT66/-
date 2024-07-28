package com.example.onlinefoodorderingsystem.controller;

import com.example.onlinefoodorderingsystem.entity.Dish;
import com.example.onlinefoodorderingsystem.entity.Result;
import com.example.onlinefoodorderingsystem.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static com.example.onlinefoodorderingsystem.utils.PasswordEncryptor.checkPassword;

@RestController
@RequestMapping("/order")
public class OrderingController {

    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    public Result<Map<Integer, Dish>> list() {
        Map<Integer, Dish> map = dishService.selectbyid();
        return Result.success(map);
    }


//    @PatchMapping("/count")
//    public Result count(@RequestHeader(name="Authorization")String token,@RequestBody Map<String, String> map)

    @PatchMapping("/count")
    public Result count(@RequestBody Map<String, String> map)
    {
        String productStr = map.get("product");

        if (productStr == null) {
            return Result.error("Invalid input: 'product' is missing");
        }

        int count;
        try {
            count = Integer.parseInt(productStr);
        } catch (NumberFormatException e) {
            return Result.error("Invalid input: 'product' should be an integer");
        }

        if (count > 0) {
            dishService.addQuantity(count);
            return Result.success("增加成功");
        } else if (count < 0) {
            int affectedRows = dishService.subtractQuantity(count);
            if (affectedRows > 0) {
                return Result.success("减少成功");
            } else {
                return Result.error("该菜品数量已经为0！");
            }
        } else {
            return Result.error("传输了错误的值");
        }
    }
}
//public Result updateCount(@RequestBody Map<String, Integer> map) {
//    Integer productId = map.get("productId");
//    Integer change = map.get("change");
//
//    if (productId == null || change == null) {
//        return Result.error("Invalid input: 'productId' and 'change' are required");
//    }
//
//    try {
//        if (change > 0) {
//            dishService.addQuantity(productId, change);
//            return Result.success("增加成功");
//        } else if (change < 0) {
//            int affectedRows = dishService.subtractQuantity(productId, -change);
//            if (affectedRows > 0) {
//                return Result.success("减少成功");
//            } else {
//                return Result.error("该菜品数量已经为0！");
//            }
//        } else {
//            return Result.error("无效的数量");
//        }
//    } catch (Exception e) {
//        return Result.error("Error updating quantity");
//    }
//}
//
//}

